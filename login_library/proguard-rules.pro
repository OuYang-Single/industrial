# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
# Please add these rules to your existing keep rules in order to suppress warnings.
# This is generated automatically by the Android Gradle plugin.
-dontwarn javax.lang.model.element.Element

# The proguard configuration file for the following section is E:\project\s\xiaoe-player\login_library\build\intermediates\default_proguard_files\global\proguard-android-optimize.txt-8.3.0
# This is a configuration file for ProGuard.
# http://proguard.sourceforge.net/index.html#manual/usage.html
#
# Starting with version 2.2 of the Android plugin for Gradle, this file is distributed together with
# the plugin and unpacked at build-time. The files in $ANDROID_HOME are no longer maintained and
# will be ignored by new version of the Android plugin for Gradle.

# Optimizations: If you don't want to optimize, use the proguard-android.txt configuration file
# instead of this one, which turns off the optimization flags.
-allowaccessmodification

# Preserve some attributes that may be required for reflection.
-keepattributes AnnotationDefault,
                EnclosingMethod,
                InnerClasses,
                RuntimeVisibleAnnotations,
                RuntimeVisibleParameterAnnotations,
                RuntimeVisibleTypeAnnotations,
                Signature

-keep public class com.google.vending.licensing.ILicensingService
-keep public class com.android.vending.licensing.ILicensingService
-keep public class com.google.android.vending.licensing.ILicensingService
-dontnote com.android.vending.licensing.ILicensingService
-dontnote com.google.vending.licensing.ILicensingService
-dontnote com.google.android.vending.licensing.ILicensingService

# For native methods, see https://www.guardsquare.com/manual/configuration/examples#native
-keepclasseswithmembernames,includedescriptorclasses class * {
    native <methods>;
}

# Keep setters in Views so that animations can still work.
-keepclassmembers public class * extends android.view.View {
    void set*(***);
    *** get*();
}

# We want to keep methods in Activity that could be used in the XML attribute onClick.
-keepclassmembers class * extends android.app.Activity {
    public void *(android.view.View);
}

# For enumeration classes, see https://www.guardsquare.com/manual/configuration/examples#enumerations
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keepclassmembers class * implements android.os.Parcelable {
    public static final ** CREATOR;
}

# Preserve annotated Javascript interface methods.
-keepclassmembers class * {
    @android.webkit.JavascriptInterface <methods>;
}

# The support libraries contains references to newer platform versions.
# Don't warn about those in case this app is linking against an older
# platform version. We know about them, and they are safe.
-dontnote android.support.**
-dontnote androidx.**
-dontwarn android.support.**
-dontwarn androidx.**

# Understand the @Keep support annotation.
-keep class android.support.annotation.Keep

-keep @android.support.annotation.Keep class * {*;}

-keepclasseswithmembers class * {
    @android.support.annotation.Keep <methods>;
}

-keepclasseswithmembers class * {
    @android.support.annotation.Keep <fields>;
}

-keepclasseswithmembers class * {
    @android.support.annotation.Keep <init>(...);
}

# These classes are duplicated between android.jar and org.apache.http.legacy.jar.
-dontnote org.apache.http.**
-dontnote android.net.http.**

# These classes are duplicated between android.jar and core-lambda-stubs.jar.
-dontnote java.lang.invoke.**

# End of content from E:\project\s\xiaoe-player\login_library\build\intermediates\default_proguard_files\global\proguard-android-optimize.txt-8.3.0
# The proguard configuration file for the following section is E:\project\s\xiaoe-player\login_library\proguard-rules.pro
# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
# Please add these rules to your existing keep rules in order to suppress warnings.
# This is generated automatically by the Android Gradle plugin.
-dontwarn javax.lang.model.element.Element
# End of content from E:\project\s\xiaoe-player\login_library\proguard-rules.pro
# The proguard configuration file for the following section is E:\project\s\xiaoe-player\login_library\build\intermediates\aapt_proguard_file\release\generateReleaseLibraryProguardRules\aapt_rules.txt
# Generated by the gradle plugin
-keep class androidx.constraintlayout.widget.ConstraintLayout { <init>(...); }
-keep class com.hjq.shape.layout.ShapeLinearLayout { <init>(...); }
-keep class com.hjq.shape.layout.ShapeRelativeLayout { <init>(...); }
-keep class com.hjq.shape.view.ShapeTextView { <init>(...); }
-keep class com.ijcsj.login_library.ui.LogInActivity { <init>(...); }

# End of content from E:\project\s\xiaoe-player\login_library\build\intermediates\aapt_proguard_file\release\generateReleaseLibraryProguardRules\aapt_rules.txt
# The proguard configuration file for the following section is E:\project\s\xiaoe-player\common_library\build\intermediates\consumer_proguard_dir\release\exportReleaseConsumerProguardFiles\lib0\proguard.txt

# End of content from E:\project\s\xiaoe-player\common_library\build\intermediates\consumer_proguard_dir\release\exportReleaseConsumerProguardFiles\lib0\proguard.txt
# The proguard configuration file for the following section is E:\project\s\xiaoe-player\ui_library\build\intermediates\consumer_proguard_dir\release\exportReleaseConsumerProguardFiles\lib0\proguard.txt

# End of content from E:\project\s\xiaoe-player\ui_library\build\intermediates\consumer_proguard_dir\release\exportReleaseConsumerProguardFiles\lib0\proguard.txt
# The proguard configuration file for the following section is E:\project\s\xiaoe-player\service_library\build\intermediates\consumer_proguard_dir\release\exportReleaseConsumerProguardFiles\lib0\proguard.txt

# End of content from E:\project\s\xiaoe-player\service_library\build\intermediates\consumer_proguard_dir\release\exportReleaseConsumerProguardFiles\lib0\proguard.txt
# The proguard configuration file for the following section is C:\Users\Administrator\.gradle\caches\transforms-3\b1c62bb435a50a909723050016406759\transformed\databinding-runtime-8.3.0\proguard.txt
-dontwarn androidx.databinding.ViewDataBinding
-dontwarn androidx.databinding.ViewDataBinding$LiveDataListener

# instant apps load these via reflection so we need to keep them.
-keep public class * extends androidx.databinding.DataBinderMapper

# End of content from C:\Users\Administrator\.gradle\caches\transforms-3\b1c62bb435a50a909723050016406759\transformed\databinding-runtime-8.3.0\proguard.txt
# The proguard configuration file for the following section is C:\Users\Administrator\.gradle\caches\transforms-3\8b40b35e7b615f4f0db211f7cce6eb83\transformed\jetified-XPopup-2.9.19\proguard.txt
# Generated keep rule for Lifecycle observer adapter.
-if class com.lxj.xpopup.core.BasePopupView {
    <init>(...);
}
-keep class com.lxj.xpopup.core.BasePopupView_LifecycleAdapter {
    <init>(...);
}

# End of content from C:\Users\Administrator\.gradle\caches\transforms-3\8b40b35e7b615f4f0db211f7cce6eb83\transformed\jetified-XPopup-2.9.19\proguard.txt
# The proguard configuration file for the following section is C:\Users\Administrator\.gradle\caches\transforms-3\1a13eb2fa7c70c4b08428e752bb9c3d0\transformed\material-1.10.0\proguard.txt
# Copyright (C) 2015 The Android Open Source Project
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

# CoordinatorLayout resolves the behaviors of its child components with reflection.
-keep public class * extends androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior {
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>();
}

# Make sure we keep annotations for CoordinatorLayout's DefaultBehavior
-keepattributes RuntimeVisible*Annotation*

# Copyright (C) 2018 The Android Open Source Project
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

# AppCompatViewInflater reads the viewInflaterClass theme attribute which then
# reflectively instantiates MaterialComponentsViewInflater using the no-argument
# constructor. We only need to keep this constructor and the class name if
# AppCompatViewInflater is also being kept.
-if class androidx.appcompat.app.AppCompatViewInflater
-keep class com.google.android.material.theme.MaterialComponentsViewInflater {
    <init>();
}


# End of content from C:\Users\Administrator\.gradle\caches\transforms-3\1a13eb2fa7c70c4b08428e752bb9c3d0\transformed\material-1.10.0\proguard.txt
# The proguard configuration file for the following section is C:\Users\Administrator\.gradle\caches\transforms-3\402d3455a44849f706f1e318b6d09b96\transformed\jetified-DialogX-0.0.49\proguard.txt

# End of content from C:\Users\Administrator\.gradle\caches\transforms-3\402d3455a44849f706f1e318b6d09b96\transformed\jetified-DialogX-0.0.49\proguard.txt
# The proguard configuration file for the following section is C:\Users\Administrator\.gradle\caches\transforms-3\c0b6214d7304d4afa1712f910bdf46a7\transformed\jetified-android-startup-1.1.0\proguard.txt

-keep public class * extends com.rousetime.android_startup.AndroidStartup { *; }
-keep class * implements com.rousetime.android_startup.provider.StartupProviderConfig { *; }

# End of content from C:\Users\Administrator\.gradle\caches\transforms-3\c0b6214d7304d4afa1712f910bdf46a7\transformed\jetified-android-startup-1.1.0\proguard.txt
# The proguard configuration file for the following section is C:\Users\Administrator\.gradle\caches\transforms-3\64966dd02369208967a5ea5236cb859d\transformed\appcompat-1.6.1\proguard.txt
# Copyright (C) 2018 The Android Open Source Project
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

# aapt is not able to read app::actionViewClass and app:actionProviderClass to produce proguard
# keep rules. Add a commonly used SearchView to the keep list until b/109831488 is resolved.
-keep class androidx.appcompat.widget.SearchView { <init>(...); }

# Never inline methods, but allow shrinking and obfuscation.
-keepclassmembernames,allowobfuscation,allowshrinking class androidx.appcompat.widget.AppCompatTextViewAutoSizeHelper$Impl* {
  <methods>;
}

# End of content from C:\Users\Administrator\.gradle\caches\transforms-3\64966dd02369208967a5ea5236cb859d\transformed\appcompat-1.6.1\proguard.txt
# The proguard configuration file for the following section is C:\Users\Administrator\.gradle\caches\transforms-3\6066d6ce2b1ac3d2f3b7e0e07447953d\transformed\jetified-glide-4.13.0\proguard.txt
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep class * extends com.bumptech.glide.module.AppGlideModule {
 <init>(...);
}
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
-keep class com.bumptech.glide.load.data.ParcelFileDescriptorRewinder$InternalRewinder {
  *** rewind();
}

# Uncomment for DexGuard only
#-keepresourcexmlelements manifest/application/meta-data@value=GlideModule

# End of content from C:\Users\Administrator\.gradle\caches\transforms-3\6066d6ce2b1ac3d2f3b7e0e07447953d\transformed\jetified-glide-4.13.0\proguard.txt
# The proguard configuration file for the following section is C:\Users\Administrator\.gradle\caches\transforms-3\e7d4ecf46798c9c8220cb36a4bc7275b\transformed\jetified-play-services-gcm-17.0.0\proguard.txt
# Ensure that proguard will not strip the handleIntent method.
-keepclassmembers class com.google.android.gms.gcm.GcmListenerService {
    public void handleIntent(android.content.Intent);
}

# End of content from C:\Users\Administrator\.gradle\caches\transforms-3\e7d4ecf46798c9c8220cb36a4bc7275b\transformed\jetified-play-services-gcm-17.0.0\proguard.txt
# The proguard configuration file for the following section is C:\Users\Administrator\.gradle\caches\transforms-3\4ad705477f78025220d720184bd1c7ad\transformed\jetified-play-services-base-17.0.0\proguard.txt
# b/35135904 Ensure that proguard will not strip the mResultGuardian.
-keepclassmembers class com.google.android.gms.common.api.internal.BasePendingResult {
  com.google.android.gms.common.api.internal.BasePendingResult$ReleasableResultGuardian mResultGuardian;
}

# End of content from C:\Users\Administrator\.gradle\caches\transforms-3\4ad705477f78025220d720184bd1c7ad\transformed\jetified-play-services-base-17.0.0\proguard.txt
# The proguard configuration file for the following section is C:\Users\Administrator\.gradle\caches\transforms-3\3f1af4dc3cb5ec5f32ad0bd3eb475b24\transformed\jetified-play-services-basement-17.0.0\proguard.txt
# Proguard flags for consumers of the Google Play services SDK
# https://developers.google.com/android/guides/setup#add_google_play_services_to_your_project

# Keep SafeParcelable NULL value, needed for reflection by DowngradeableSafeParcel
-keepclassmembers public class com.google.android.gms.common.internal.safeparcel.SafeParcelable {
    public static final *** NULL;
}

# Needed for Parcelable/SafeParcelable classes & their creators to not get renamed, as they are
# found via reflection.
-keep class com.google.android.gms.common.internal.ReflectedParcelable
-keepnames class * implements com.google.android.gms.common.internal.ReflectedParcelable
-keepclassmembers class * implements android.os.Parcelable {
  public static final *** CREATOR;
}

# Keep the classes/members we need for client functionality.
-keep @interface androidx.annotation.Keep
-keep @androidx.annotation.Keep class *
-keepclasseswithmembers class * {
  @androidx.annotation.Keep <fields>;
}
-keepclasseswithmembers class * {
  @androidx.annotation.Keep <methods>;
}

# Keep the names of classes/members we need for client functionality.
-keep @interface com.google.android.gms.common.annotation.KeepName
-keepnames @com.google.android.gms.common.annotation.KeepName class *
-keepclassmembernames class * {
  @com.google.android.gms.common.annotation.KeepName *;
}

# Keep Dynamite API entry points
-keep @interface com.google.android.gms.common.util.DynamiteApi
-keep @com.google.android.gms.common.util.DynamiteApi public class * {
  public <fields>;
  public <methods>;
}

# Needed when building against pre-Marshmallow SDK.
-dontwarn android.security.NetworkSecurityPolicy

# Needed when building against Marshmallow SDK.
-dontwarn android.app.Notification

# Protobuf has references not on the Android boot classpath
-dontwarn sun.misc.Unsafe
-dontwarn libcore.io.Memory

# Internal Google annotations for generating Proguard keep rules.
-dontwarn com.google.android.apps.common.proguard.UsedBy*

# Annotations referenced by the SDK but whose definitions are contained in
# non-required dependencies.
-dontwarn javax.annotation.**
-dontwarn org.checkerframework.**

# End of content from C:\Users\Administrator\.gradle\caches\transforms-3\3f1af4dc3cb5ec5f32ad0bd3eb475b24\transformed\jetified-play-services-basement-17.0.0\proguard.txt
# The proguard configuration file for the following section is C:\Users\Administrator\.gradle\caches\transforms-3\1a67209376e5fb24adfca62ad9e39792\transformed\fragment-1.3.6\proguard.txt
# Copyright (C) 2020 The Android Open Source Project
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

# The default FragmentFactory creates Fragment instances using reflection
-if public class ** extends androidx.fragment.app.Fragment
-keepclasseswithmembers,allowobfuscation public class <1> {
    public <init>();
}

# End of content from C:\Users\Administrator\.gradle\caches\transforms-3\1a67209376e5fb24adfca62ad9e39792\transformed\fragment-1.3.6\proguard.txt
# The proguard configuration file for the following section is C:\Users\Administrator\.gradle\caches\transforms-3\5023db758e7e9991c5373327a2f1060b\transformed\jetified-savedstate-1.2.1\proguard.txt
# Copyright (C) 2019 The Android Open Source Project
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

-keepclassmembers,allowobfuscation class * implements androidx.savedstate.SavedStateRegistry$AutoRecreated {
    <init>();
}

# End of content from C:\Users\Administrator\.gradle\caches\transforms-3\5023db758e7e9991c5373327a2f1060b\transformed\jetified-savedstate-1.2.1\proguard.txt
# The proguard configuration file for the following section is C:\Users\Administrator\.gradle\caches\transforms-3\6783b4d20c04e0b95015c39cdbabc045\transformed\jetified-lifecycle-viewmodel-savedstate-2.6.1\proguard.txt
-keepclassmembers,allowobfuscation class * extends androidx.lifecycle.ViewModel {
    <init>(androidx.lifecycle.SavedStateHandle);
}

-keepclassmembers,allowobfuscation class * extends androidx.lifecycle.AndroidViewModel {
    <init>(android.app.Application,androidx.lifecycle.SavedStateHandle);
}

# End of content from C:\Users\Administrator\.gradle\caches\transforms-3\6783b4d20c04e0b95015c39cdbabc045\transformed\jetified-lifecycle-viewmodel-savedstate-2.6.1\proguard.txt
# The proguard configuration file for the following section is C:\Users\Administrator\.gradle\caches\transforms-3\e204f843e7427eb79c1d446b6589c25b\transformed\lifecycle-viewmodel-2.6.1\proguard.txt
-keepclassmembers,allowobfuscation class * extends androidx.lifecycle.ViewModel {
    <init>();
}

-keepclassmembers,allowobfuscation class * extends androidx.lifecycle.AndroidViewModel {
    <init>(android.app.Application);
}

# End of content from C:\Users\Administrator\.gradle\caches\transforms-3\e204f843e7427eb79c1d446b6589c25b\transformed\lifecycle-viewmodel-2.6.1\proguard.txt
# The proguard configuration file for the following section is C:\Users\Administrator\.gradle\caches\transforms-3\8b1183c0342c6ba6d599908504f49043\transformed\work-runtime-2.7.1\proguard.txt
-keep class * extends androidx.work.Worker
-keep class * extends androidx.work.InputMerger
# Keep all constructors on ListenableWorker, Worker (also marked with @Keep)
-keep public class * extends androidx.work.ListenableWorker {
    public <init>(...);
}
# We need to keep WorkerParameters for the ListenableWorker constructor
-keep class androidx.work.WorkerParameters

# End of content from C:\Users\Administrator\.gradle\caches\transforms-3\8b1183c0342c6ba6d599908504f49043\transformed\work-runtime-2.7.1\proguard.txt
# The proguard configuration file for the following section is C:\Users\Administrator\.gradle\caches\transforms-3\ae08d1e312d53b2b67f4b93f2ef37bbc\transformed\jetified-lifecycle-process-2.6.1\proguard.txt
# this rule is need to work properly when app is compiled with api 28, see b/142778206
-keepclassmembers class * extends androidx.lifecycle.EmptyActivityLifecycleCallbacks { *; }
# End of content from C:\Users\Administrator\.gradle\caches\transforms-3\ae08d1e312d53b2b67f4b93f2ef37bbc\transformed\jetified-lifecycle-process-2.6.1\proguard.txt
# The proguard configuration file for the following section is C:\Users\Administrator\.gradle\caches\transforms-3\867a16df4d3b932596135c76578424b5\transformed\coordinatorlayout-1.1.0\proguard.txt
# Copyright (C) 2016 The Android Open Source Project
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

# CoordinatorLayout resolves the behaviors of its child components with reflection.
-keep public class * extends androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior {
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>();
}

# Make sure we keep annotations for CoordinatorLayout's DefaultBehavior and ViewPager's DecorView
-keepattributes *Annotation*

# End of content from C:\Users\Administrator\.gradle\caches\transforms-3\867a16df4d3b932596135c76578424b5\transformed\coordinatorlayout-1.1.0\proguard.txt
# The proguard configuration file for the following section is C:\Users\Administrator\.gradle\caches\transforms-3\2bfaaa343533bab3820d775faffe054b\transformed\transition-1.2.0\proguard.txt
# Copyright (C) 2017 The Android Open Source Project
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

# Keep a field in transition that is used to keep a reference to weakly-referenced object
-keepclassmembers class androidx.transition.ChangeBounds$* extends android.animation.AnimatorListenerAdapter {
  androidx.transition.ChangeBounds$ViewBounds mViewBounds;
}

# End of content from C:\Users\Administrator\.gradle\caches\transforms-3\2bfaaa343533bab3820d775faffe054b\transformed\transition-1.2.0\proguard.txt
# The proguard configuration file for the following section is C:\Users\Administrator\.gradle\caches\transforms-3\bd5010a03f537d88f879b0df0192bf37\transformed\vectordrawable-animated-1.1.0\proguard.txt
# Copyright (C) 2016 The Android Open Source Project
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

# keep setters in VectorDrawables so that animations can still work.
-keepclassmembers class androidx.vectordrawable.graphics.drawable.VectorDrawableCompat$* {
   void set*(***);
   *** get*();
}

# End of content from C:\Users\Administrator\.gradle\caches\transforms-3\bd5010a03f537d88f879b0df0192bf37\transformed\vectordrawable-animated-1.1.0\proguard.txt
# The proguard configuration file for the following section is C:\Users\Administrator\.gradle\caches\transforms-3\b3def15da5efe5dc860b59be7658682d\transformed\recyclerview-1.2.1\proguard.txt
# Copyright (C) 2015 The Android Open Source Project
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

# When layoutManager xml attribute is used, RecyclerView inflates
#LayoutManagers' constructors using reflection.
-keep public class * extends androidx.recyclerview.widget.RecyclerView$LayoutManager {
    public <init>(android.content.Context, android.util.AttributeSet, int, int);
    public <init>();
}

-keepclassmembers class androidx.recyclerview.widget.RecyclerView {
    public void suppressLayout(boolean);
    public boolean isLayoutSuppressed();
}
# End of content from C:\Users\Administrator\.gradle\caches\transforms-3\b3def15da5efe5dc860b59be7658682d\transformed\recyclerview-1.2.1\proguard.txt
# The proguard configuration file for the following section is C:\Users\Administrator\.gradle\caches\transforms-3\e9b41202ef5b7dca97140517278bcd70\transformed\media-1.0.0\proguard.txt
# Copyright (C) 2017 The Android Open Source Project
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

# Prevent Parcelable objects from being removed or renamed.
-keep class android.support.v4.media.** implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}

# Prevent Parcelable objects from being removed or renamed.
-keep class androidx.media.** implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}
# End of content from C:\Users\Administrator\.gradle\caches\transforms-3\e9b41202ef5b7dca97140517278bcd70\transformed\media-1.0.0\proguard.txt
# The proguard configuration file for the following section is C:\Users\Administrator\.gradle\caches\transforms-3\76a6830f70699363edf9e4a2579cce47\transformed\core-1.10.1\proguard.txt
# Never inline methods, but allow shrinking and obfuscation.
-keepclassmembernames,allowobfuscation,allowshrinking class androidx.core.view.ViewCompat$Api* {
  <methods>;
}
-keepclassmembernames,allowobfuscation,allowshrinking class androidx.core.view.WindowInsetsCompat$*Impl* {
  <methods>;
}
-keepclassmembernames,allowobfuscation,allowshrinking class androidx.core.app.NotificationCompat$*$Api*Impl {
  <methods>;
}
-keepclassmembernames,allowobfuscation,allowshrinking class androidx.core.os.UserHandleCompat$Api*Impl {
  <methods>;
}
-keepclassmembernames,allowobfuscation,allowshrinking class androidx.core.widget.EdgeEffectCompat$Api*Impl {
  <methods>;
}

# End of content from C:\Users\Administrator\.gradle\caches\transforms-3\76a6830f70699363edf9e4a2579cce47\transformed\core-1.10.1\proguard.txt
# The proguard configuration file for the following section is C:\Users\Administrator\.gradle\caches\transforms-3\c465da69303309c0053b088627efe6bf\transformed\lifecycle-runtime-2.6.1\proguard.txt
-keepattributes AnnotationDefault,
                RuntimeVisibleAnnotations,
                RuntimeVisibleParameterAnnotations,
                RuntimeVisibleTypeAnnotations

-keepclassmembers enum androidx.lifecycle.Lifecycle$Event {
    <fields>;
}

-keep !interface * implements androidx.lifecycle.LifecycleObserver {
}

-keep class * implements androidx.lifecycle.GeneratedAdapter {
    <init>(...);
}

-keepclassmembers class ** {
    @androidx.lifecycle.OnLifecycleEvent *;
}

# this rule is need to work properly when app is compiled with api 28, see b/142778206
# Also this rule prevents registerIn from being inlined.
-keepclassmembers class androidx.lifecycle.ReportFragment$LifecycleCallbacks { *; }
# End of content from C:\Users\Administrator\.gradle\caches\transforms-3\c465da69303309c0053b088627efe6bf\transformed\lifecycle-runtime-2.6.1\proguard.txt
# The proguard configuration file for the following section is C:\Users\Administrator\.gradle\caches\transforms-3\4040f2367ce2dd3fdadcde9f18af1718\transformed\rules\lib\META-INF\com.android.tools\r8\coroutines.pro
# When editing this file, update the following files as well:
# - META-INF/proguard/coroutines.pro
# - META-INF/com.android.tools/proguard/coroutines.pro

# Most of volatile fields are updated with AFU and should not be mangled
-keepclassmembers class kotlinx.coroutines.** {
    volatile <fields>;
}

# Same story for the standard library's SafeContinuation that also uses AtomicReferenceFieldUpdater
-keepclassmembers class kotlin.coroutines.SafeContinuation {
    volatile <fields>;
}

# These classes are only required by kotlinx.coroutines.debug.AgentPremain, which is only loaded when
# kotlinx-coroutines-core is used as a Java agent, so these are not needed in contexts where ProGuard is used.
-dontwarn java.lang.instrument.ClassFileTransformer
-dontwarn sun.misc.SignalHandler
-dontwarn java.lang.instrument.Instrumentation
-dontwarn sun.misc.Signal

# Only used in `kotlinx.coroutines.internal.ExceptionsConstructor`.
# The case when it is not available is hidden in a `try`-`catch`, as well as a check for Android.
-dontwarn java.lang.ClassValue

# An annotation used for build tooling, won't be directly accessed.
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
# End of content from C:\Users\Administrator\.gradle\caches\transforms-3\4040f2367ce2dd3fdadcde9f18af1718\transformed\rules\lib\META-INF\com.android.tools\r8\coroutines.pro
# The proguard configuration file for the following section is C:\Users\Administrator\.gradle\caches\transforms-3\1c16c10b0e5d71435c852ff3854217c7\transformed\rules\lib\META-INF\com.android.tools\r8-from-1.6.0\coroutines.pro
# Allow R8 to optimize away the FastServiceLoader.
# Together with ServiceLoader optimization in R8
# this results in direct instantiation when loading Dispatchers.Main
-assumenosideeffects class kotlinx.coroutines.internal.MainDispatcherLoader {
    boolean FAST_SERVICE_LOADER_ENABLED return false;
}

-assumenosideeffects class kotlinx.coroutines.internal.FastServiceLoaderKt {
    boolean ANDROID_DETECTED return true;
}

# Disable support for "Missing Main Dispatcher", since we always have Android main dispatcher
-assumenosideeffects class kotlinx.coroutines.internal.MainDispatchersKt {
    boolean SUPPORT_MISSING return false;
}

# Statically turn off all debugging facilities and assertions
-assumenosideeffects class kotlinx.coroutines.DebugKt {
    boolean getASSERTIONS_ENABLED() return false;
    boolean getDEBUG() return false;
    boolean getRECOVER_STACK_TRACES() return false;
}

# End of content from C:\Users\Administrator\.gradle\caches\transforms-3\1c16c10b0e5d71435c852ff3854217c7\transformed\rules\lib\META-INF\com.android.tools\r8-from-1.6.0\coroutines.pro
# The proguard configuration file for the following section is C:\Users\Administrator\.gradle\caches\transforms-3\5d63dc272521ee0a8bef34fafcafecff\transformed\jetified-flexbox-3.0.0\proguard.txt
#
# Copyright 2016 Google Inc. All rights reserved.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

# The FlexboxLayoutManager may be set from a layout xml, in that situation the RecyclerView
# tries to instantiate the layout manager using reflection.
# This is to prevent the layout manager from being obfuscated.
-keepnames public class com.google.android.flexbox.FlexboxLayoutManager
# End of content from C:\Users\Administrator\.gradle\caches\transforms-3\5d63dc272521ee0a8bef34fafcafecff\transformed\jetified-flexbox-3.0.0\proguard.txt
# The proguard configuration file for the following section is C:\Users\Administrator\.gradle\caches\transforms-3\10f53bfcd808d500fcb70833be548b55\transformed\rules\lib\META-INF\proguard\retrofit2.pro
# Retrofit does reflection on generic parameters. InnerClasses is required to use Signature and
# EnclosingMethod is required to use InnerClasses.
-keepattributes Signature, InnerClasses, EnclosingMethod

# Retrofit does reflection on method and parameter annotations.
-keepattributes RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations

# Retain service method parameters when optimizing.
-keepclassmembers,allowshrinking,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}

# Ignore annotation used for build tooling.
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement

# Ignore JSR 305 annotations for embedding nullability information.
-dontwarn javax.annotation.**

# Guarded by a NoClassDefFoundError try/catch and only used when on the classpath.
-dontwarn kotlin.Unit

# Top-level functions that can only be used by Kotlin.
-dontwarn retrofit2.KotlinExtensions
-dontwarn retrofit2.KotlinExtensions$*

# With R8 full mode, it sees no subtypes of Retrofit interfaces since they are created with a Proxy
# and replaces all potential values with null. Explicitly keeping the interfaces prevents this.
-if interface * { @retrofit2.http.* <methods>; }
-keep,allowobfuscation interface <1>

# End of content from C:\Users\Administrator\.gradle\caches\transforms-3\10f53bfcd808d500fcb70833be548b55\transformed\rules\lib\META-INF\proguard\retrofit2.pro
# The proguard configuration file for the following section is C:\Users\Administrator\.gradle\caches\transforms-3\2ac253eb2ddad40cc4c8b8878d3d1710\transformed\jetified-cos-android-5.9.25\proguard.txt
# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/rickenwang/Library/Android/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
# cosxml
-dontwarn com.tencent.cos.**
-keep class com.tencent.cos.xml.**{*;}

#foundation
-keep class com.tencent.qcloud.core.**{*;}
-keep class com.tencent.qcloud.qcloudxml.core.**{*;}
-keep class com.tencent.qcloud.cos.**{*;}




# End of content from C:\Users\Administrator\.gradle\caches\transforms-3\2ac253eb2ddad40cc4c8b8878d3d1710\transformed\jetified-cos-android-5.9.25\proguard.txt
# The proguard configuration file for the following section is C:\Users\Administrator\.gradle\caches\transforms-3\2b7c8426839c82645d796e89bacb61b9\transformed\jetified-qcloud-cos-android-base-5.9.23\proguard.txt
# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/rickenwang/Library/Android/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
# cosxml
-dontwarn com.tencent.cos.**
-keep class com.tencent.cos.xml.**{*;}

#foundation
-keep class com.tencent.qcloud.core.**{*;}
-keep class com.tencent.qcloud.cos.**{*;}



# End of content from C:\Users\Administrator\.gradle\caches\transforms-3\2b7c8426839c82645d796e89bacb61b9\transformed\jetified-qcloud-cos-android-base-5.9.23\proguard.txt
# The proguard configuration file for the following section is C:\Users\Administrator\.gradle\caches\transforms-3\0eb650ea60df22773c3f1829c6ecc927\transformed\rules\lib\META-INF\proguard\okhttp3.pro
# JSR 305 annotations are for embedding nullability information.
-dontwarn javax.annotation.**

# A resource is loaded with a relative path so the package of this class must be preserved.
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase

# Animal Sniffer compileOnly dependency to ensure APIs are compatible with older versions of Java.
-dontwarn org.codehaus.mojo.animal_sniffer.*

# OkHttp platform used only on JVM and when Conscrypt dependency is available.
-dontwarn okhttp3.internal.platform.ConscryptPlatform

# End of content from C:\Users\Administrator\.gradle\caches\transforms-3\0eb650ea60df22773c3f1829c6ecc927\transformed\rules\lib\META-INF\proguard\okhttp3.pro
# The proguard configuration file for the following section is C:\Users\Administrator\.gradle\caches\transforms-3\3fb75e417ea1f1212baecc7a4d0b17f7\transformed\jetified-startup-runtime-1.1.1\proguard.txt
# It's important that we preserve initializer names, given they are used in the AndroidManifest.xml.
-keepnames class * extends androidx.startup.Initializer

# These Proguard rules ensures that ComponentInitializers are are neither shrunk nor obfuscated,
# and are a part of the primary dex file. This is because they are discovered and instantiated
# during application startup.
-keep class * extends androidx.startup.Initializer {
    # Keep the public no-argument constructor while allowing other methods to be optimized.
    <init>();
}

-assumenosideeffects class androidx.startup.StartupLogger { public static <methods>; }

# End of content from C:\Users\Administrator\.gradle\caches\transforms-3\3fb75e417ea1f1212baecc7a4d0b17f7\transformed\jetified-startup-runtime-1.1.1\proguard.txt
# The proguard configuration file for the following section is C:\Users\Administrator\.gradle\caches\transforms-3\ab094c9db26f702659f40192b67603c5\transformed\jetified-mmkv-1.3.3\proguard.txt
# Keep all native methods, their classes and any classes in their descriptors
-keepclasseswithmembers,includedescriptorclasses class com.tencent.mmkv.** {
    native <methods>;
    long nativeHandle;
    private static *** onMMKVCRCCheckFail(***);
    private static *** onMMKVFileLengthError(***);
    private static *** mmkvLogImp(...);
    private static *** onContentChangedByOuterProcess(***);
}

# End of content from C:\Users\Administrator\.gradle\caches\transforms-3\ab094c9db26f702659f40192b67603c5\transformed\jetified-mmkv-1.3.3\proguard.txt
# The proguard configuration file for the following section is C:\Users\Administrator\.gradle\caches\transforms-3\31c8354d06fc2ab27f7bee627fe8bc2f\transformed\room-runtime-2.2.5\proguard.txt
-keep class * extends androidx.room.RoomDatabase
-dontwarn androidx.room.paging.**

# End of content from C:\Users\Administrator\.gradle\caches\transforms-3\31c8354d06fc2ab27f7bee627fe8bc2f\transformed\room-runtime-2.2.5\proguard.txt
# The proguard configuration file for the following section is C:\Users\Administrator\.gradle\caches\transforms-3\7247518e5e19bee6475cdfe5b9b54e70\transformed\versionedparcelable-1.1.1\proguard.txt
-keep class * implements androidx.versionedparcelable.VersionedParcelable
-keep public class android.support.**Parcelizer { *; }
-keep public class androidx.**Parcelizer { *; }
-keep public class androidx.versionedparcelable.ParcelImpl

# End of content from C:\Users\Administrator\.gradle\caches\transforms-3\7247518e5e19bee6475cdfe5b9b54e70\transformed\versionedparcelable-1.1.1\proguard.txt
# The proguard configuration file for the following section is C:\Users\Administrator\.gradle\caches\transforms-3\24bc058ca8c4d8190e412c9f3ebe6bba\transformed\rules\lib\META-INF\proguard\androidx-annotations.pro
-keep,allowobfuscation @interface androidx.annotation.Keep
-keep @androidx.annotation.Keep class * {*;}

-keepclasseswithmembers class * {
    @androidx.annotation.Keep <methods>;
}

-keepclasseswithmembers class * {
    @androidx.annotation.Keep <fields>;
}

-keepclasseswithmembers class * {
    @androidx.annotation.Keep <init>(...);
}

-keepclassmembers,allowobfuscation class * {
  @androidx.annotation.DoNotInline <methods>;
}

# End of content from C:\Users\Administrator\.gradle\caches\transforms-3\24bc058ca8c4d8190e412c9f3ebe6bba\transformed\rules\lib\META-INF\proguard\androidx-annotations.pro
# The proguard configuration file for the following section is C:\Users\Administrator\.gradle\caches\transforms-3\ba574f85877f4eca4207a60fe3c1388b\transformed\rules\lib\META-INF\proguard\okio.pro
# Animal Sniffer compileOnly dependency to ensure APIs are compatible with older versions of Java.
-dontwarn org.codehaus.mojo.animal_sniffer.*

# End of content from C:\Users\Administrator\.gradle\caches\transforms-3\ba574f85877f4eca4207a60fe3c1388b\transformed\rules\lib\META-INF\proguard\okio.pro
# The proguard configuration file for the following section is C:\Users\Administrator\.gradle\caches\transforms-3\2a4c78c6bcecd2d8a71ad89f7f78822f\transformed\jetified-adapter_plus-3.07.00\proguard.txt

# End of content from C:\Users\Administrator\.gradle\caches\transforms-3\2a4c78c6bcecd2d8a71ad89f7f78822f\transformed\jetified-adapter_plus-3.07.00\proguard.txt
# The proguard configuration file for the following section is C:\Users\Administrator\.gradle\caches\transforms-3\3d86f95b7fa97e4bc6e27a35292d1602\transformed\jetified-AndroidAutoSize-v1.2.1\proguard.txt
# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-keep class me.jessyan.autosize.** { *; }
-keep interface me.jessyan.autosize.** { *; }
# End of content from C:\Users\Administrator\.gradle\caches\transforms-3\3d86f95b7fa97e4bc6e27a35292d1602\transformed\jetified-AndroidAutoSize-v1.2.1\proguard.txt
# The proguard configuration file for the following section is C:\Users\Administrator\.gradle\caches\transforms-3\1121005afd9ed3dabb762524f6d402a7\transformed\rules\lib\META-INF\proguard\rxjava3.pro
-dontwarn java.util.concurrent.Flow*
# End of content from C:\Users\Administrator\.gradle\caches\transforms-3\1121005afd9ed3dabb762524f6d402a7\transformed\rules\lib\META-INF\proguard\rxjava3.pro
# The proguard configuration file for the following section is C:\Users\Administrator\.gradle\caches\transforms-3\5c5775b9230b190b7968d064da47c61a\transformed\jetified-qcloud-track-1.0.2\proguard.txt
-keep class net.jpountz.lz4.** { *; }

# beacon
-keep class com.tencent**qimei.** { *;}
-keep class com.tencent.qmsp.oaid2.** {*;}
-keep class com.tencent.beacon.** { *;}

# R8
-dontwarn com.tencent.beacon.event.open.BeaconConfig$Builder
-dontwarn com.tencent.beacon.event.open.BeaconConfig
-dontwarn com.tencent.beacon.event.open.BeaconEvent$Builder
-dontwarn com.tencent.beacon.event.open.BeaconEvent
-dontwarn com.tencent.beacon.event.open.BeaconReport
-dontwarn com.tencent.beacon.event.open.EventResult
-dontwarn com.tencent.beacon.event.open.EventType

-dontwarn com.tencent.qimei.sdk.IQimeiSDK
-dontwarn com.tencent.qimei.sdk.QimeiSDK
-dontwarn com.tencent.qimei.strategy.terminal.ITerminalStrategy
-dontwarn com.tencent.qimei.codez.FalconSdk
-dontwarn com.tencent.qimei.codez.IFalconSdk
-dontwarn com.tencent.qimei.codez.shell.UserInfoType
-dontwarn com.tencent.smtt.export.external.extension.interfaces.IX5WebViewExtension
-dontwarn com.tencent.smtt.sdk.WebSettings
-dontwarn com.tencent.smtt.sdk.WebView
-dontwarn com.tencent.smtt.sdk.WebViewClient

-dontwarn com.tencentcloudapi.cls.android.producer.AsyncProducerClient
-dontwarn com.tencentcloudapi.cls.android.producer.AsyncProducerConfig
-dontwarn com.tencentcloudapi.cls.android.producer.Callback
-dontwarn com.tencentcloudapi.cls.android.producer.common.LogItem
-dontwarn com.tencentcloudapi.cls.android.producer.errors.ProducerException
# End of content from C:\Users\Administrator\.gradle\caches\transforms-3\5c5775b9230b190b7968d064da47c61a\transformed\jetified-qcloud-track-1.0.2\proguard.txt
# The proguard configuration file for the following section is <unknown>
-keep class **.R
-keep class **.R$* {*;}
# End of content from <unknown>