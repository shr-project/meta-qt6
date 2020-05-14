inherit qt6-paths

create_sdk_files_prepend () {
    # Generate a qt.conf file to be deployed with the SDK
    qtconf=${SDK_OUTPUT}${SDKPATHNATIVE}${QT6_INSTALL_BINDIR}/qt.conf
    touch $qtconf
    echo '[Paths]' >> $qtconf
    echo 'Prefix = ${prefix}' >> $qtconf
    echo 'Headers = ${QT6_INSTALL_INCLUDEDIR}' >> $qtconf
    echo 'Libraries = ${QT6_INSTALL_LIBDIR}' >> $qtconf
    echo 'ArchData = ${QT6_INSTALL_ARCHDATADIR}' >> $qtconf
    echo 'Data = ${QT6_INSTALL_DATADIR}' >> $qtconf
    echo 'Binaries = ${QT6_INSTALL_BINDIR}' >> $qtconf
    echo 'LibraryExecutables = ${QT6_INSTALL_LIBEXECDIR}' >> $qtconf
    echo 'Plugins = ${QT6_INSTALL_PLUGINSDIR}' >> $qtconf
    echo 'Qml2Imports = ${QT6_INSTALL_QMLDIR}' >> $qtconf
    echo 'Translations = ${QT6_INSTALL_TRANSLATIONSDIR}' >> $qtconf
    echo 'Documentation = ${QT6_INSTALL_DOCDIR}' >> $qtconf
    echo 'Settings = ${QT6_INSTALL_SYSCONFDIR}' >> $qtconf
    echo 'Examples = ${QT6_INSTALL_EXAMPLESDIR}' >> $qtconf
    echo 'Tests = ${QT6_INSTALL_TESTSDIR}' >> $qtconf
    echo 'HostPrefix = ${SDKPATHNATIVE}${prefix}' >> $qtconf
    echo 'HostData = ${SDKTARGETSYSROOT}${QT6_INSTALL_ARCHDATADIR}' >> $qtconf
    echo 'HostBinaries = ${SDKPATHNATIVE}${QT6_INSTALL_BINDIR}' >> $qtconf
    echo 'HostLibraries = ${SDKPATHNATIVE}${QT6_INSTALL_LIBDIR}' >> $qtconf
    echo 'Sysroot = ${SDKTARGETSYSROOT}' >> $qtconf
    echo 'HostSpec = linux-oe-g++' >> $qtconf
    echo 'TargetSpec = linux-oe-g++' >> $qtconf

    install -d ${SDK_OUTPUT}${SDKPATHNATIVE}/environment-setup.d
    script=${SDK_OUTPUT}${SDKPATHNATIVE}/environment-setup.d/qt6.sh
    touch $script
    echo 'export OE_QMAKE_CFLAGS="$CFLAGS"' >> $script
    echo 'export OE_QMAKE_CXXFLAGS="$CXXFLAGS"' >> $script
    echo 'export OE_QMAKE_LDFLAGS="$LDFLAGS"' >> $script
    echo 'export OE_QMAKE_CC="$CC"' >> $script
    echo 'export OE_QMAKE_CXX="$CXX"' >> $script
    echo 'export OE_QMAKE_LINK="$CXX"' >> $script
    echo 'export OE_QMAKE_AR="$AR" >> $script
    echo 'export OE_QMAKE_STRIP="$STRIP" >> $script

    # Generate a toolchain file for using Qt without running setup-environment script
    cat > ${SDK_OUTPUT}${SDKPATHNATIVE}/usr/share/cmake/Qt6Toolchain.cmake <<EOF
set(ENV{CC} "${TARGET_PREFIX}gcc ${TARGET_CC_ARCH} --sysroot=${SDKTARGETSYSROOT}")
set(ENV{CXX} "${TARGET_PREFIX}g++ ${TARGET_CC_ARCH} --sysroot=${SDKTARGETSYSROOT}")

set(ENV{CFLAGS} "${TARGET_CFLAGS}")
set(ENV{CXXFLAGS} "${TARGET_CXXFLAGS}")

set(ENV{OECORE_NATIVE_SYSROOT} "${SDKPATHNATIVE}")
set(ENV{OECORE_TARGET_SYSROOT} "${SDKTARGETSYSROOT}")
set(ENV{SDKTARGETSYSROOT} "${SDKTARGETSYSROOT}")

set(CMAKE_TOOLCHAIN_FILE "${SDKPATHNATIVE}/usr/share/cmake/OEToolchainConfig.cmake")
include("\${CMAKE_TOOLCHAIN_FILE}")
EOF
}

# default debug prefix map isn't valid in the SDK
DEBUG_PREFIX_MAP = ""
SECURITY_CFLAGS = ""
