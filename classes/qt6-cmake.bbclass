inherit cmake python3native

DEPENDS_prepend = "qtbase-native "

# Install locations:
# Executables [PREFIX/bin]
QT6_INSTALL_BINDIR = "${bindir}"
# Header files [PREFIX/include]
QT6_INSTALL_INCLUDEDIR = "${includedir}"
# Libraries [PREFIX/lib]
QT6_INSTALL_LIBDIR = "${libdir}"
# Arch-dependent data [PREFIX]
QT6_INSTALL_ARCHDATADIR = "${libdir}"
# Plugins [ARCHDATADIR/plugins]
QT6_INSTALL_PLUGINSDIR = "${libdir}/plugins"
# Helper programs ARCHDATADIR/libexec otherwise
QT6_INSTALL_LIBEXECDIR = "${libexecdir}"
# QML2 imports [ARCHDATADIR/qml]
QT6_INSTALL_QMLDIR = "${libdir}/qml"
# "Arch-independent data [PREFIX]
QT6_INSTALL_DATADIR = "${datadir}"
# Documentation [DATADIR/doc]
QT6_INSTALL_DOCDIR = "${datadir}/doc"
# Translations [DATADIR/translations]
QT6_INSTALL_TRANSLATIONSDIR = "${datadir}/translations"
# Settings used by Qt programs [PREFIX/etc/xdg]
QT6_INSTALL_SYSCONFDIR = "${sysconfdir}/xdg"
# Examples [PREFIX/examples]
QT6_INSTALL_EXAMPLESDIR = "${datadir}/examples"
# Tests [PREFIX/tests]
QT6_INSTALL_TESTSDIR = "${datadir}/tests"
# Module description files directory [DATADIR/modules]
QT6_INSTALL_DESCRIPTIONSDIR = "${datadir}/modules"
# Mkspecs files [PREFIX/mkspecs]
QT6_INSTALL_MKSPECSDIR = "${datadir}/mkspecs"

EXTRA_OECMAKE += "\
    -DQT_CMAKE_DEBUG_EXTEND_TARGET=ON \
    -DQT_HOST_PATH:PATH=${RECIPE_SYSROOT_NATIVE}/usr/ \
"

EXTRA_OECMAKE += "\
    -DINSTALL_ARCHDATADIR:PATH=${@os.path.relpath(d.getVar('QT6_INSTALL_ARCHDATADIR'), d.getVar('prefix') + '/')} \
    -DINSTALL_BINDIR:PATH=${@os.path.relpath(d.getVar('QT6_INSTALL_BINDIR'), d.getVar('prefix') + '/')} \
    -DINSTALL_DATADIR:PATH=${@os.path.relpath(d.getVar('QT6_INSTALL_DATADIR'), d.getVar('prefix') + '/')} \
    -DINSTALL_DESCRIPTIONSDIR:PATH=${@os.path.relpath(d.getVar('QT6_INSTALL_DESCRIPTIONSDIR'), d.getVar('prefix') + '/')} \
    -DINSTALL_DOCDIR:PATH=${@os.path.relpath(d.getVar('QT6_INSTALL_DOCDIR'), d.getVar('prefix') + '/')} \
    -DINSTALL_EXAMPLESDIR:PATH=${@os.path.relpath(d.getVar('QT6_INSTALL_EXAMPLESDIR'), d.getVar('prefix') + '/')} \
    -DINSTALL_INCLUDEDIR:PATH=${@os.path.relpath(d.getVar('QT6_INSTALL_INCLUDEDIR'), d.getVar('prefix') + '/')} \
    -DINSTALL_LIBDIR:PATH=${@os.path.relpath(d.getVar('QT6_INSTALL_LIBDIR'), d.getVar('prefix') + '/')} \
    -DINSTALL_LIBEXECDIR:PATH=${@os.path.relpath(d.getVar('QT6_INSTALL_LIBEXECDIR'), d.getVar('prefix') + '/')} \
    -DINSTALL_PLUGINSDIR:PATH=${@os.path.relpath(d.getVar('QT6_INSTALL_PLUGINSDIR'), d.getVar('prefix') + '/')} \
    -DINSTALL_QMLDIR:PATH=${@os.path.relpath(d.getVar('QT6_INSTALL_QMLDIR'), d.getVar('prefix') + '/')} \
    -DINSTALL_SYSCONFDIR:PATH=${@os.path.relpath(d.getVar('QT6_INSTALL_SYSCONFDIR'), d.getVar('prefix') + '/')} \
    -DINSTALL_TESTSDIR:PATH=${@os.path.relpath(d.getVar('QT6_INSTALL_TESTSDIR'), d.getVar('prefix') + '/')} \
    -DINSTALL_TRANSLATIONSDIR:PATH=${@os.path.relpath(d.getVar('QT6_INSTALL_TRANSLATIONSDIR'), d.getVar('prefix') + '/')} \
    -DINSTALL_MKSPECSDIR:PATH=${@os.path.relpath(d.getVar('QT6_INSTALL_MKSPECSDIR'), d.getVar('prefix') + '/')} \
"
