# Man10CommonLibs
man10のプラグインを利用するために必要なlibrary
# Usage
## Gradle
1. Man10CommonLibsをビルドして使うプロジェクトに追加する。
2. `dependencies {
        compile fileTree(dir: 'libs', include: '*.jar')
}`
をbuild.gradleに追記する。
3. `depend:  - Man10CommonLibs`をplugin.ymlに追記する。
4. サーバーのpluginsにMan10CommonLibsを導入しておく。
## Maven
mavenのサポートはしていません。
#現在追加されているファイルなど
- Man10用MySQLManager
