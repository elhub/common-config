package no.elhub.common.konfig

/**
 * Error thrown when an error has occured in common-konfig
 */
class KonfigException(override val message: String, cause: Exception? = null) : RuntimeException(message, cause)
