import com.android.build.api.dsl.VariantDimension

plugins {
    alias(appPlugins.plugins.com.android.application)
    alias(appPlugins.plugins.org.jetbrains.kotlin.android)
    alias(appPlugins.plugins.compose.compiler)
    alias(appPlugins.plugins.kotlin.parcelize)
    alias(appPlugins.plugins.kotlin.ksp)
    alias(appPlugins.plugins.hilt.android)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = ConfigData.NAMESPACE
    compileSdk = ConfigData.COMPILE_SDK

    defaultConfig {
        applicationId = ConfigData.APPLICATION_ID
        minSdk = ConfigData.MIN_SDK
        targetSdk = ConfigData.TARGET_SDK
        multiDexEnabled = true
        testInstrumentationRunner = ConfigData.TEST_INSTRUMENTATION_RUNNER
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    signingConfigs {
        create(ConfigData.DEBUG_CONFIG) {
            keyAlias = KeyStoreData.KEY_ALIAS
            keyPassword = KeyStoreData.KEYSTORE_PASSWORD
            storeFile = file(KeyStoreData.KEYSTORE_FILE_PATH)
            storePassword = KeyStoreData.KEYSTORE_PASSWORD
        }
        create(ConfigData.RELEASE_CONFIG) {
            keyAlias = KeyStoreData.KEY_ALIAS
            keyPassword = KeyStoreData.KEYSTORE_PASSWORD
            storeFile = file(KeyStoreData.KEYSTORE_FILE_PATH)
            storePassword = KeyStoreData.KEYSTORE_PASSWORD
        }
    }

    buildTypes {
        getByName(ConfigData.DEBUG) {
            isMinifyEnabled = false
            isDebuggable = true
            isShrinkResources = false
            isJniDebuggable = false
            signingConfig = signingConfigs.getByName(ConfigData.DEBUG_CONFIG)
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
        getByName(ConfigData.RELEASE) {
            isMinifyEnabled = false
            isDebuggable = false
            isShrinkResources = false
            isJniDebuggable = false
            signingConfig = signingConfigs.getByName(ConfigData.RELEASE_CONFIG)
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }

    flavorDimensions += ConfigData.Flavors.FLAVOR

    productFlavors {
        create(ConfigData.Flavors.DEV) {
            applicationIdSuffix = ConfigData.Flavors.APP_ID_SUFFIX_DEV
            dimension = ConfigData.Flavors.FLAVOR
            versionCode = ConfigData.DevVersion.VERSION_CODE
            versionName = ConfigData.DevVersion.VERSION_NAME
            signingConfig = signingConfigs.getByName(ConfigData.DEBUG_CONFIG)
            buildConfigString(key = UrlConfig.Names.BASE_URL, value = UrlConfig.Dev.BASE_URL)
            buildConfigString(key = UrlConfig.Names.MERCHANT_CODE_NAME, value = UrlConfig.Dev.MERCHANT_CODE)
            resValue("string", "app_name", ConfigData.Flavors.DEV.plus(" ${ConfigData.APP_NAME}"))
        }

        create(ConfigData.Flavors.SIT) {
            applicationIdSuffix = ConfigData.Flavors.APP_ID_SUFFIX_SIT
            dimension = ConfigData.Flavors.FLAVOR
            versionCode = ConfigData.SitVersion.VERSION_CODE
            versionName = ConfigData.SitVersion.VERSION_NAME
            signingConfig = signingConfigs.getByName(ConfigData.DEBUG_CONFIG)
            buildConfigString(key = UrlConfig.Names.BASE_URL, value = UrlConfig.Sit.BASE_URL)
            buildConfigString(key = UrlConfig.Names.MERCHANT_CODE_NAME, value = UrlConfig.Sit.MERCHANT_CODE)
            resValue("string", "app_name", ConfigData.Flavors.SIT.plus(" ${ConfigData.APP_NAME}"))
        }


        create(ConfigData.Flavors.PROD) {
            dimension = ConfigData.Flavors.FLAVOR
            versionCode = ConfigData.ProdVersion.VERSION_CODE
            versionName = ConfigData.ProdVersion.VERSION_NAME
            signingConfig = signingConfigs.getByName(ConfigData.RELEASE_CONFIG)
            buildConfigString(key = UrlConfig.Names.BASE_URL, value = UrlConfig.Prod.BASE_URL)
            buildConfigString(key = UrlConfig.Names.MERCHANT_CODE_NAME, value = UrlConfig.Prod.MERCHANT_CODE)
            resValue("string", "app_name", ConfigData.APP_NAME)
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = ConfigData.KOTLIN_VERSION
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = ConfigData.KOTLIN_COMPILER_VERSION
    }
    packaging {
        resources {
            excludes += ConfigData.EXCLUDE_PATH
        }
    }
    lint {
        checkAllWarnings = true
        warningsAsErrors = true
        checkReleaseBuilds = false
        abortOnError = false
    }
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.androidx.lifecycle)
    implementation(libs.androidx.activity)
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.compose.lifecycle)
    implementation(libs.compose.ui.graphics)
    implementation(libs.compose.ui.preview)
    implementation(libs.compose.material3)

    //Compose navigation and Viewmodel
    implementation(libs.lifecycle.livedata)
    implementation(libs.lifecycle.viewmodel)
    implementation(libs.lifecycle.viewmodel.savedstate)
    implementation(libs.navigation.compose)
    implementation(libs.kotlinx.serialization.json)

    //System UI Controller - Accompanist
    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.accompanist.navigation.animation)
    implementation(libs.accompanist.insets)

    //Hilt Android
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.androidx.ui.text.google.fonts)
    implementation(libs.androidx.constraintlayout.compose)
    ksp(libs.hilt.compiler)

    //Retrofit and logging interceptor, okhttp
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)

    //Chucker
    implementation(libs.chuckerDebug)
//  releaseImplementation(libs.chuckerRelease)

    //Encrypted Shared preferences and credentials APIS
    implementation(libs.security.crypto)
    implementation(libs.security.identity.credential)
    implementation(libs.security.app.authenticator)

    //Paging 3
    implementation(libs.paging.runtime)
    implementation(libs.paging.compose)
    implementation(libs.accompanist.flowlayout)
    implementation(libs.accompanist.pager)
    implementation(libs.accompanist.pager.indicators)

    //Coil
    implementation(libs.coil)
    implementation(libs.coil.compose)
    implementation(libs.coil.gif)

    //Testing Sdk's
    testImplementation(libs.junit.junit.test)
    androidTestImplementation(libs.androidx.test.ext.test)
    androidTestImplementation(libs.androidx.test.espresso.test)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.compose.ui.test)
    debugImplementation(libs.compose.ui.tooling.test)
    debugImplementation(libs.compose.ui.manifest.test)

    //permission
    implementation(libs.accompanist.permissions)
    // timer log
    implementation(libs.utils.timber)

    implementation(libs.jsoup)
    implementation(libs.kotlinx.collections.immutable)
    implementation(libs.androidx.core.splashscreen)
}

fun VariantDimension.buildConfigBoolean(key: String, value: String) {
    buildConfigField("Boolean", key, value)
}

fun VariantDimension.buildConfigString(key: String, value: String) {
    buildConfigField("String", key, value)
}
