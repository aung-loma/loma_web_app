
object ConfigData {
    const val COMPILE_SDK = 35
    const val MIN_SDK = 24
    const val TARGET_SDK = 35
    const val NAMESPACE = "aungbophyoe.github.io.basemvijc"
    const val APPLICATION_ID = "com.basemvijc"
    const val APP_NAME = "BaseMVI"
    const val DEBUG_CONFIG = "debugConfig"
    const val RELEASE_CONFIG = "releaseConfig"
    const val DEBUG = "debug"
    const val RELEASE = "release"
    const val KOTLIN_VERSION = "17"
    const val KOTLIN_COMPILER_VERSION = "1.5.12"
    const val EXCLUDE_PATH = "/META-INF/{AL2.0,LGPL2.1}"
    const val TEST_INSTRUMENTATION_RUNNER = "androidx.test.runner.AndroidJUnitRunner"

    interface Flavors {
        companion object {
            const val FLAVOR = "flavor"
            const val APP_ID_SUFFIX_DEV = ".dev"
            const val APP_ID_SUFFIX_SIT = ".sit"
            const val DEV = "dev_"
            const val SIT = "sit_"
            const val PROD = "prod_"
        }
    }

    interface DevVersion {
        companion object {
            private const val MAJOR = 1
            private const val MINOR = 0
            private const val PATCH = 0

            val VERSION_CODE: Int
                get() = 1

            val VERSION_NAME: String
                get() = getVersionName(MAJOR, MINOR, PATCH)
        }
    }

    interface SitVersion {
        companion object {
            private const val MAJOR = 1
            private const val MINOR = 0
            private const val PATCH = 0

            val VERSION_CODE: Int
                get() = 1

            val VERSION_NAME: String
                get() = getVersionName(MAJOR, MINOR, PATCH)
        }
    }

    interface ProdVersion {
        companion object {
            private const val MAJOR = 1
            private const val MINOR = 0
            private const val PATCH = 0

            val VERSION_CODE: Int
                get() = 1

            val VERSION_NAME: String
                get() = getVersionName(MAJOR, MINOR, PATCH)
        }
    }

    fun getVersionName(major: Int, minor: Int, patch: Int) =
        "$major.$minor.$patch"
}