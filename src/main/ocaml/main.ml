let f () = flush stdout
open Core.Std
open Async.Std

open Types
open Sodium


let string_of_key key =
  let key = Box.Bytes.of_public_key key in
  let buf = Buffer.create 64 in
  Bytes.iter
    (fun b ->
       Printf.bprintf buf "%02x" (Char.to_int b))
    key;
  Buffer.contents buf


(*
let rec io_loop sock ip port key channel_keys =
  let target = Printf.sprintf "%s:%d" ip port in
  (*Printf.printf "Sending NodesRequest to %s %s\n" target (string_of_key key);*)

  let nonce = Box.random_nonce () in
  let (sk, pk) = Box.random_keypair () in

  let ping_id = 0x8765432101234567L in

  let channel_key = Box.precompute sk key in
  channel_keys := StringMap.add !channel_keys ~key:target ~data:(key, channel_key);

  let out = Output.create 1500 in
  Packet.encode (channel_key, nonce) out nodes_request (
    pk @<
    nonce @<
    pk @<
    ping_id
  );

  let packet = Output.to_bytes out |> Bytes.to_string in

  let addr = Unix.(ADDR_INET (Inet_addr.of_string ip, port)) in

  Unix.sendto sock packet 0 (String.length packet) [] addr >>= fun res ->
  (*Printf.printf "sendto: %d\n" res;*)

  let packet_data = Bytes.create 1500 in
  Lwt_unix.recvfrom sock packet_data 0 (Bytes.length packet_data) [] >>= fun (res, addr) ->
  let from =
    match addr with
    | Unix.ADDR_INET (addr, port) ->
        Unix.string_of_inet_addr addr ^ ":" ^ string_of_int port
    | Unix.ADDR_UNIX file ->
        file
  in
  (*Printf.printf "recvfrom: %d bytes from %s\n" res from;*)

  let packet = Input.of_bytes (Bytes.sub packet_data 0 res) in
  let (key, channel_key) = StringMap.find from !channel_keys in

  try
    match Bytes.get packet_data 0 with
    | '\x04' ->
        print_endline @@ "Got NodesResponse from " ^ from;

        let ctx, packet, decoded =
          Packet.decode (channel_key, nonce) packet
            nodes_response
        in

        let next =
          match decoded with
          | pk', (nonce', (nodes, ping_id')) ->
              assert (Box.equal_public_keys pk' key);
              assert (ping_id' = ping_id);
              List.fold_left
                (fun acc -> function
                   | (A (proto, ipv4), (port, dht_key)) ->
                       assert (Bytes.length ipv4 = 4);
                       let ip =
                         Printf.sprintf "%d.%d.%d.%d"
                           (Char.code @@ Bytes.get ipv4 0)
                           (Char.code @@ Bytes.get ipv4 1)
                           (Char.code @@ Bytes.get ipv4 2)
                           (Char.code @@ Bytes.get ipv4 3)
                       in
                       (*print_endline ip;*)
                       (ip, port, dht_key) :: acc
                   | (B (proto, ipv6), (port, dht_key)) ->
                       assert (Bytes.length ipv6 = 16);
                       (*print_endline "yoy";*)
                       acc
                ) [] nodes
        in

        Lwt_list.iter_p
          (fun (ip, port, dht_key) ->
             io_loop sock ip port dht_key channel_keys
          )
          next

    | '\x00' ->
        print_endline @@ "Got EchoRequest from " ^ from;
        let ctx, packet, decoded =
          Packet.decode (channel_key, nonce) packet
            echo_request
        in

        let nonce, ping_id = snd decoded in

        let out = Output.create 1500 in
        Packet.encode (channel_key, nonce) out echo_response (
          pk @<
          nonce @<
          ping_id
        );

        let packet = Output.to_bytes out in

        Lwt_unix.sendto sock packet 0 (Bytes.length packet) [] addr >>= fun res ->
        (*print_endline "Sent EchoResponse";*)
        return ()

    | x ->
        Printf.printf "Unhandled packet: %02x\n"
          (Char.code x);
        return ()

  with Sodium.Verification_failure ->
    (*print_endline @@ "Sodium.Verification_failure for packet from " ^ ip;*)
    return ()


let () =
  let sock = Lwt_unix.(socket PF_INET SOCK_DGRAM 0) in
  Lwt_unix.(bind sock (ADDR_INET (Unix.inet_addr_any, 33445)));

  let ip = "144.76.60.215" in
  let port = 33445 in
  let key =
    Box.Bytes.to_public_key (
      Bytes.of_string
        "\x04\x11\x9E\x83\x5D\xF3\xE7\x8B\xAC\xF0\xF8\x42\x35\xB3\x00\x54\x6A\xF8\xB9\x36\xF0\x35\x18\x5E\x2A\x8E\x9E\x0A\x67\xC8\x92\x4F"
    )
  in

  Lwt_main.run (io_loop sock ip port key (ref StringMap.empty))
*)


let main =
  let dht = ref (Dht.create ()) in

  let sonOfRa = {
    (*n_addr = "144.76.60.215";*)
    n_proto = Protocol.UDP;
    n_addr = InetAddr.IPv4 (Option.value_exn (InetAddr.ipv4_of_string
                                                "\x90\x4c\x3c\xd7"));
    n_port = Option.value_exn (Port.of_int 33445);
    n_key =
      PublicKey.of_string (
        "\x04\x11\x9E\x83\x5D\xF3\xE7\x8B" ^
        "\xAC\xF0\xF8\x42\x35\xB3\x00\x54" ^
        "\x6A\xF8\xB9\x36\xF0\x35\x18\x5E" ^
        "\x2A\x8E\x9E\x0A\x67\xC8\x92\x4F"
      )
  } in

  dht := Dht.add_node !dht sonOfRa;

  let sendto =
    match Udp.sendto () with
    | Error error ->
        Error.raise error
    | Ok sendto ->
        sendto
  in

  Udp.bind (Socket.Address.Inet.create Unix.Inet_addr.bind_any ~port:33445) >>=
  fun sock ->

  let channel_key =
    (PublicKeyMap.find_exn !dht.dht_nodes sonOfRa.n_key).cn_ckey
  in

  let request = NodesRequest.(
    make ~public_key:!dht.dht_pk ~channel_key {
      key = !dht.dht_pk;
      ping_id = 0x8765432101234567L;
    }
  ) in
  print_endline (Iobuf.to_string_hum ~bounds:`Window request);

  let sent =
    let ip = InetAddr.to_string sonOfRa.n_addr in
    sendto (Socket.fd sock) request
      (Socket.Address.Inet.create (Unix.Inet_addr.of_string ip)
         ~port:(Port.to_int sonOfRa.n_port))
  in

  sent >>= fun () ->
  Udp.recvfrom_loop (Socket.fd sock)
    (fun buf from ->
       let buf = Iobuf.read_only buf in

       match Iobuf.Peek.uint8 ~pos:0 buf with
       | 0x00 ->
           print_endline @@ "Got EchoRequest from " ^
                            Socket.Address.Inet.to_string from;
           print_endline @@ Iobuf.to_string_hum ~bounds:`Window buf;
       | 0x01 ->
           print_endline @@ "Got EchoResponse from " ^
                            Socket.Address.Inet.to_string from;
           print_endline @@ Iobuf.to_string_hum ~bounds:`Window buf;
       | 0x02 ->
           print_endline @@ "Got NodesRequest from " ^
                            Socket.Address.Inet.to_string from;
           print_endline @@ Iobuf.to_string_hum ~bounds:`Window buf;
       | 0x04 ->
           print_endline @@ "Got NodesResponse from " ^
                            Socket.Address.Inet.to_string from;

           (*
           let s = Unix.gettimeofday () in
           for i = 0 to 1000000 do
             NodesResponse.unpack !dht (Iobuf.sub buf)
           done;
           let e = Unix.gettimeofday () in
           Printf.printf "time: %f\n" (e -. s);
           f ();
           *)

           begin match NodesResponse.unpack !dht buf with
             | None -> failwith "Format error in NodesResponse"
             | Some decoded ->
                 assert (decoded.NodesResponse.ping_id = 0x8765432101234567L);

                 List.iter decoded.NodesResponse.nodes
                   ~f:(
                     fun node ->
                       print_endline (InetAddr.to_string node.n_addr)
                   )
           end

       | code ->
           failwith @@ Printf.sprintf "Unhandled packet type: 0x%02x" code
    )


let () =
  (main >>> fun () -> shutdown 0);
  never_returns (Scheduler.go ())
