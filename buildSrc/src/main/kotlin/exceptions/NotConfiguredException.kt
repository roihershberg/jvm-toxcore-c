import java.lang.RuntimeException

class NotConfiguredException(notConfigured: String, method: String) : RuntimeException() {
    override val message: String? = "$notConfigured is not configured. $method() method must be called."
}
