package ltd.toktok.tox4j.errors

import java.lang.RuntimeException

public class ToxBootstrapException(
    public val errorCode: ToxErrBootstrap,
) : RuntimeException(errorCode.name)
