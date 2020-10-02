plugins {
    id("com.android.application")
    kotlin("android")
    id("org.jmailen.kotlinter") version "3.2.0"
}

android {
    compileSdkVersion(30)

    defaultConfig {
        applicationId = "com.vanpra.composematerialdialogs"
        minSdkVersion(21)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    packagingOptions {
        exclude("META-INF/DEPENDENCIES")
        exclude("META-INF/LICENSE")
        exclude("META-INF/LICENSE.txt")
        exclude("META-INF/license.txt")
        exclude("META-INF/NOTICE")
        exclude("META-INF/NOTICE.txt")
        exclude("META-INF/notice.txt")
        exclude("META-INF/ASL2.0")
        exclude("META-INF/*.kotlin_module")
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
        kotlinCompilerExtensionVersion = Versions.compose
        kotlinCompilerVersion = Versions.kotlin
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().all {
    kotlinOptions {
        useIR = true
        jvmTarget = "1.8"
        freeCompilerArgs = listOf(
            "-Xallow-jvm-ir-dependencies",
            "-Xskip-prerelease-check",
            "-Xopt-in=kotlin.Experimental"
        )
    }
}

dependencies {
    // implementation("com.vanpra.compose-material-dialogs:core:0.2.2")
    // implementation("com.vanpra.compose-material-dialogs:datetime:0.2.2")
    // implementation("com.vanpra.compose-material-dialogs:color:0.2.2")

    implementation(project(":core"))
    implementation(project(":color"))
    implementation(project(":datetime"))

    implementation("dev.chrisbanes.accompanist:accompanist-coil:${Versions.accompanist}")

    implementation(kotlin("stdlib-jdk8", Versions.kotlin))
    implementation("androidx.core:core-ktx:1.3.1")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("com.google.android.material:material:1.2.1")

    implementation("androidx.compose.foundation:foundation-layout:${Versions.compose}")
    implementation("androidx.compose.material:material:${Versions.compose}")
    implementation("androidx.ui:ui-tooling:${Versions.compose}")

    testImplementation("junit:junit:4.13")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
}

