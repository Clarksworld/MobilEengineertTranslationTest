plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.mobile.mobileengineertranslationtest"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.mobile.mobileengineertranslationtest"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {


    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

        implementation( "com.google.android.material:material:1.11.0")
        implementation ("androidx.viewpager2:viewpager2:1.0.0")



        implementation ("androidx.navigation:navigation-fragment-ktx:2.7.7")
        implementation ("androidx.navigation:navigation-ui-ktx:2.7.7")

    //circular imageview
    implementation("de.hdodenhof:circleimageview:3.1.0")

    implementation ("com.google.android.flexbox:flexbox:3.0.0")





}