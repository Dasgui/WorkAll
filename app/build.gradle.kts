plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("com.google.devtools.ksp") version "1.8.10-1.0.9" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.22"
}

android {
    namespace = "com.example.ozonchick_new_version"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.ozonchick_new_version"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
//
    implementation(platform("io.github.jan-tennert.supabase:bom:2.2.0-alpha-2"))
    implementation("io.github.jan-tennert.supabase:postgrest-kt")
    implementation("io.github.jan-tennert.supabase:realtime-kt")
    implementation("io.ktor:ktor-client-android:2.3.8")

    implementation ("io.github.pilgr:paperdb:2.7.2")

    implementation ("com.google.code.gson:gson:2.8.5")

    implementation("com.google.accompanist:accompanist-pager:0.34.0")

    implementation("io.insert-koin:koin-core:1.3.1")
    implementation("io.insert-koin:koin-android:1.3.1")
    implementation("io.insert-koin:koin-annotations:1.3.1")
    implementation("io.insert-koin:koin-ksp-compiler:1.3.1")
    implementation("io.insert-koin:koin-androidx-compose:3.5.0")
    implementation("io.coil-kt:coil-compose:2.6.0")

    implementation("androidx.navigation:navigation-compose:2.7.7")

    implementation("cafe.adriel.voyager:voyager-navigator:1.0.0-rc10")
    implementation("cafe.adriel.voyager:voyager-androidx:1.0.0-rc10")
    implementation("cafe.adriel.voyager:voyager-koin:${rootProject.extra["voyagerKoinVersion"]}")



    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}