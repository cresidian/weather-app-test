# Keep all classes in your application
-keep class !com.example.weatherapptest.** {*;}

# Keep all application-specific attributes
-keepattributes *Annotation*

# Keep all enum classes
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# Keep all Parcelable classes (if you're using Parcelable)
-keep class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}

# Keep all Serializable classes (if you're using Serializable)
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

# Keep your ViewModel classes and their methods (change package name accordingly)
-keep class com.example.weatherapptest.presentation.weatherdetails.** {
    *;
}

# Keep your ViewModelFactory classes and their methods (change package name accordingly)
-keep class com.example.weatherapptest.presentation.weatherdetails.**Factory {
    *;
}

# Keep your Hilt modules (if using Hilt for dependency injection)
-keep class com.example.weatherapptest.di.** { *; }
-keepclassmembers class com.example.weatherapptest.di.** { *; }

# Optionally, you can keep specific classes from third-party libraries that are required
# to function properly in your app (e.g., Gson, Retrofit, etc.)
# Uncomment and update the following lines if necessary:
#-keep class com.google.gson.** { *; }
#-keep class retrofit2.** { *; }

# Optionally, you can keep specific classes from the Android SDK that are required
# for your app to function properly (e.g., AndroidX libraries, etc.)
# Uncomment and update the following lines if necessary:
#-keep class androidx.** { *; }
#-keep class androidx.lifecycle.** { *; }

# Optionally, you can keep specific classes from other third-party libraries that
# are required for your app to function properly
# Uncomment and update the following lines if necessary:
#-keep class com.example.some_library.** { *; }

# Allow usage of some classes that are dynamically created by Kotlin
-dontnote kotlinx.serialization.**
-keepnames class kotlinx.serialization.** { *; }
-keepclassmembers class kotlin.Metadata { *; }
