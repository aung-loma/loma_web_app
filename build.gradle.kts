// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(appPlugins.plugins.com.android.application) apply false
    alias(appPlugins.plugins.org.jetbrains.kotlin.android) apply false
    alias(appPlugins.plugins.compose.compiler) apply false
    alias(appPlugins.plugins.kotlin.parcelize) apply false
    alias(appPlugins.plugins.kotlin.ksp) apply false
    alias(appPlugins.plugins.hilt.android) apply false
    alias(appPlugins.plugins.protobuf) apply false
    alias(appPlugins.plugins.google.gms.google.services) apply false
    alias(libs.plugins.nav.safe.args) apply false
}