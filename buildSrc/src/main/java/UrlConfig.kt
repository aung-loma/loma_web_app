object UrlConfig {
    interface Names {
        companion object {
            const val BASE_URL = "BASE_URL"
            const val ENABLE_LOGGER = "ENABLE_LOGGER"
            const val ENABLE_CRASHLYTICS = "ENABLE_CRASHLYTICS"
            const val MERCHANT_CODE_NAME = "MERCHANT_CODE"
        }
    }

    interface Dev {
        companion object {
            const val BASE_URL = "\"https://elitemartkh.com/api/\""
            const val MERCHANT_CODE = "\"1111\""
        }
    }

    interface Sit {
        companion object {
            const val BASE_URL = "\"https://elitemartkh.com/api/\""
            const val MERCHANT_CODE = "\"1111\""
        }
    }

    interface Prod {
        companion object {
            const val BASE_URL = "\"https://elitemartkh.com/api/\""
            const val MERCHANT_CODE = "\"1111\""
        }
    }
}