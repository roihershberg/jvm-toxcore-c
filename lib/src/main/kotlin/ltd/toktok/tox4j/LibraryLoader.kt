package ltd.toktok.tox4j

import mu.KotlinLogging
import java.io.File
import java.io.InputStream

private val logger = KotlinLogging.logger {}

internal object LibraryLoader {
    private fun withTempFile(name: String, block: (File) -> Boolean): Boolean {
        val indexOfLastDot = name.lastIndexOf(".")
        val prefix = name.substring(0, indexOfLastDot)
        val suffix = name.substring(indexOfLastDot)
        val file = File.createTempFile(prefix, suffix)
        file.deleteOnExit()
        return try {
            block(file)
        } finally {
            // This may fail if the OS doesn't support deleting files that are in use, but deleteOnExit
            // will ensure that it is cleaned up on normal JVM termination.
            file.delete()
        }
    }

    private fun withResource(name: String, block: (InputStream) -> Boolean): Boolean {
        val stream = javaClass.getResourceAsStream(name)
        return if (stream == null) {
            logger.warn { "Resource '$name' not found" }
            false
        } else {
            try {
                block(stream)
            } finally {
                stream.close()
            }
        }
    }

    /**
     * Load a native library from an existing location by copying it to a new, temporary location and loading
     * that new library.
     *
     * @param location A [File] pointing to the existing library.
     */
    private fun loadFromSystem(location: File): Boolean =
        withTempFile(location.name) { libraryFile ->
            logger.debug { "Copying $location to $libraryFile" }
            location.copyTo(libraryFile)

            System.load(libraryFile.path)
            true
        }

    /**
     * Load a library from a linked resource jar by copying it to a temporary location and then loading that
     * temporary file.
     *
     * @param name The library name without "lib" prefix or extension like "dll".
     */
    private fun loadFromJar(name: String): Boolean {
        val resourceName = "${OSChecker.platform}/${System.mapLibraryName(name)}"
        logger.debug { "Loading $name from resource: $resourceName" }
        val location = File(resourceName)
        return withTempFile(location.name) { libraryFile ->
            if (withResource(resourceName) { stream ->
                    logger.debug { "Copying $resourceName to ${libraryFile.path}" }
                    stream.use { input ->
                        libraryFile.outputStream().use { output ->
                            input.copyTo(output)
                        }
                    }
                    true
                }) {
                System.load(libraryFile.path)
                true
            } else {
                false
            }
        }
    }

    fun load(name: String): Unit = synchronized(this) {
        try {
            System.loadLibrary(name)
        } catch (e: UnsatisfiedLinkError) {
            logger.debug {
                "Could not load native library '$name' (${e.message}). " +
                        "java.library.path = ${System.getProperty("java.library.path")}."
            }

            val loadingSuccessful = e.message?.let { error ->
                val alreadyLoaded = "Native Library (.+) already loaded in another classloader".toRegex()
                val notFoundDalvik = "Couldn't load .+ from loader .+ findLibrary returned null".toRegex()
                val notFoundJvm = "no .+ in java.library.path.*".toRegex()

                alreadyLoaded.find(error)?.let { matchResult ->
                    val (location) = matchResult.destructured
                    logger.warn { "$error copying file and loading again" }
                    loadFromSystem(File(location))
                } ?: run {
                    if (notFoundJvm.matches(error)) {
                        loadFromJar(name)
                    } else if (notFoundDalvik.matches(error)) {
                        logger.error(e) { "Could not load native library '$name'; giving up." }
                        false
                    } else {
                        logger.error(e) { "Unhandled UnsatisfiedLinkError: '$error'." }
                        false
                    }
                }
            }

            if (loadingSuccessful == true) {
                logger.debug("Loading '$name' successful")
            } else {
                throw e
            }
        }
    }
}
