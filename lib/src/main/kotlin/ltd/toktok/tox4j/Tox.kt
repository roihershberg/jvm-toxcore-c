package ltd.toktok.tox4j

class Tox {
    companion object {
        init {
            LibraryLoader.load("tox4j-jni")
        }

        /**
         * The major version number.
         *
         * Incremented when the API or ABI changes in an incompatible way.
         */
        @JvmStatic
        val versionMajor: Int
            external get

        /**
         * The minor version number.
         *
         * Incremented when functionality is added without  breaking the API or ABI.
         * Set to 0 when the major version number is incremented.
         */
        @JvmStatic
        val versionMinor: Int
            external get

        /**
         * The patch or revision number.
         *
         * Incremented when bugfixes are applied without changing any functionality or
         * API or ABI.
         */
        @JvmStatic
        val versionPatch: Int
            external get

        /**
         * The size of a Tox Public Key in bytes.
         */
        @JvmStatic
        val publicKeySize: Int
            external get

        /**
         * The size of a Tox Secret Key in bytes.
         */
        @JvmStatic
        val secretKeySize: Int
            external get

        /**
         * The size of a Tox Conference unique id in bytes.
         */
        @JvmStatic
        val conferenceIdSize: Int
            external get

        /**
         * The size of the nospam in bytes when written in a Tox address.
         */
        @JvmStatic
        val nospamSize: Int
            external get

        /**
         * The size of a Tox address in bytes.
         *
         * Tox addresses are in the format
         * `[Public Key (<publicKeySize> bytes)][nospam (4 bytes)][checksum (2 bytes)]`.
         *
         * The checksum is computed over the Public Key and the nospam value. The first
         * byte is an XOR of all the even bytes (0, 2, 4, ...), the second byte is an
         * XOR of all the odd bytes (1, 3, 5, ...) of the Public Key and nospam.
         */
        @JvmStatic
        val addressSize: Int
            external get

        /**
         * Maximum length of a nickname in bytes.
         */
        @JvmStatic
        val maxNameLength: Int
            external get

        /**
         * Maximum length of a status message in bytes.
         */
        @JvmStatic
        val maxStatusMessageLength: Int
            external get

        /**
         * Maximum length of a friend request message in bytes.
         */
        @JvmStatic
        val maxFriendRequestLength: Int
            external get

        /**
         * Maximum length of a single message after which it should be split.
         */
        @JvmStatic
        val maxMessageLength: Int
            external get

        /**
         * Maximum size of custom packets.
         */
        @JvmStatic
        val maxCustomPacketSize: Int
            external get

        /**
         * Maximum file name length for file transfers.
         */
        @JvmStatic
        val maxFilenameLength: Int
            external get

        /**
         * Maximum length of a hostname, e.g. proxy or bootstrap node names.
         */
        @JvmStatic
        val maxHostnameLength: Int
            external get
    }
}
