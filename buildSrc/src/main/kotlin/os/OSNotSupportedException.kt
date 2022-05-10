import java.lang.RuntimeException

class OSNotSupportedException : RuntimeException() {
    override val message: String? = "OS is not supported"
}
