/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

group = "tech.thdev.compose.extensions.buildlogic"

// Configure the build-logic plugins to target JDK 17
// This matches the JDK used to build the project, and is not related to what is running on device.
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}

dependencies {
    compileOnly(libs.plugin.android.gradlePlugin)
    compileOnly(libs.plugin.compose.gradlePlugin)
    compileOnly(libs.plugin.kotlin.gradlePlugin)
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        /**
         * Application 관련
         */
        register("androidApplication") {
            id = "tech.thdev.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }

        /**
         * Library 관련
         */
        register("androidLibraryCompose") {
            id = "tech.thdev.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("androidLibraryComposeFeature") {
            id = "tech.thdev.android.library.compose.feature"
            implementationClass = "AndroidLibraryComposeFeatureConventionPlugin"
        }
        register("androidLibrary") {
            id = "tech.thdev.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }

        /**
         * Kotlin 라이브러리
         */
        register("kotlinLibrary") {
            id = "tech.thdev.kotlin.library"
            implementationClass = "KotlinLibraryConventionPlugin"
        }
    }
}