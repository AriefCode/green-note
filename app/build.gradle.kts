plugins {
    alias(libs.plugins.android.application)
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.projectkelompok"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.example.projectkelompok"
        minSdk = 24
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = false
            }
        }
    }
    buildFeatures {
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.cardview)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.core.ktx)
    implementation(libs.glide)
    implementation(libs.material)
    //penambahan dependensi
    implementation("androidx.gridlayout:gridlayout:1.0.0") // GridLayout
    implementation("androidx.viewpager2:viewpager2:1.1.0") // ViewPager2
    implementation("com.tbuonomo:dotsindicator:5.1.0")     // DotsIndicator
    //nambah lagi
    implementation("androidx.recyclerview:recyclerview:1.3.2") // RecyclerView
    //room database
    val roomVersion = "2.8.4"
    implementation("androidx.room:room-runtime:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")
    ksp("androidx.room:room-compiler:$roomVersion")
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
}