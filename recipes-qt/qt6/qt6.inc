PACKAGECONFIG[examples] = "-DBUILD_EXAMPLES=ON,-DBUILD_EXAMPLES=OFF,"
PACKAGECONFIG[tests] = "-DBUILD_TESTING=ON,-DBUILD_TESTING=OFF,"
PACKAGECONFIG[qtquickcompiler] = "-Dqtquickcompiler=ON,-Dqtquickcompiler=OFF,qtdeclarative-native"

# If Qt6 (qtbase) is machine specific, then everything will be,
# because the (initial) qtbase configuration becomes part of Qt5/qmake
python __anonymous() {
    barch = d.getVar("BUILD_ARCH", True) or ''
    tarch = d.getVar("TARGET_ARCH", True) or ''
    # do not do anything if we are building a native package
    if barch != tarch:
        tarch = d.getVar("QT_PACKAGES_ARCH", True) or ''
        if tarch:
            d.setVar("PACKAGE_ARCH", tarch)
}

# if building static Qt5, add qtdeclarative-native dependency to all recipes
# that depend on qtdeclarative as it's required for qmlimportscannertool
python __anonymous() {
    if bb.utils.contains('DISTRO_FEATURES', "qt5-static", True, False, d):
        if bb.utils.contains('DEPENDS', "qtdeclarative", True, False, d):
            d.appendVar("DEPENDS", " qtdeclarative-native")
}

# Many examples come with libraries installed outside of standard libdir,
# suppress QA check complaining
#INSANE_SKIP_${PN}-dbg += "libdir"
#INSANE_SKIP_${PN}-plugins += "libdir"
#INSANE_SKIP_${PN}-examples += "libdir staticdev dev-so"

PACKAGES =. "${PN}-qmldesigner ${PN}-qmlplugins ${PN}-tools ${PN}-plugins ${PN}-mkspecs ${PN}-examples "

ALLOW_EMPTY_${PN} = "1"
ALLOW_EMPTY_${PN}-plugins = "1"
ALLOW_EMPTY_${PN}-qmlplugins = "1"

RRECOMMENDS_${PN} = " \
    ${PN}-plugins \
    ${PN}-qmlplugins \
    "
RRECOMMENDS_${PN}_class-native = ""

RRECOMMENDS_${PN}-dev = " \
    ${PN} \
    ${PN}-mkspecs \
    ${PN}-qmldesigner \
    "

OE_QMAKE_PATH_QML = "/usr/lib/qml"
OE_QMAKE_PATH_BINS = "/usr/bin"
OE_QMAKE_PATH_PLUGINS = "/usr/lib/plugins"
OE_QMAKE_PATH_LIBEXECS = "/usr/libexec"
OE_QMAKE_PATH_LIBS = "/usr/lib"
OE_QMAKE_PATH_EXAMPLES = "/usr/examples"
OE_QMAKE_PATH_ARCHDATA = "/usr"

# extra packages
FILES_${PN}-qmldesigner += " \
    ${OE_QMAKE_PATH_QML}/*/designer \
    ${OE_QMAKE_PATH_QML}/*/*/designer \
    ${OE_QMAKE_PATH_QML}/*/*/*/designer \
"

# qmlplugins 1-4 levels of subdirs qmldir + *{.so,*.qmltypes,*.qml,*.qmlc,*.js,*.jsc}
FILES_${PN}-qmlplugins = " \
    ${OE_QMAKE_PATH_QML}/*.qmltypes \
    ${OE_QMAKE_PATH_QML}/*/*${SOLIBSDEV} \
    ${OE_QMAKE_PATH_QML}/*/qmldir \
    ${OE_QMAKE_PATH_QML}/*/*.qmltypes \
    ${OE_QMAKE_PATH_QML}/*/*.qml \
    ${OE_QMAKE_PATH_QML}/*/*.qmlc \
    ${OE_QMAKE_PATH_QML}/*/*.js \
    ${OE_QMAKE_PATH_QML}/*/*.jsc \
    ${OE_QMAKE_PATH_QML}/*/*.png \
    ${OE_QMAKE_PATH_QML}/*/*/*${SOLIBSDEV} \
    ${OE_QMAKE_PATH_QML}/*/*/qmldir \
    ${OE_QMAKE_PATH_QML}/*/*/*.qmltypes \
    ${OE_QMAKE_PATH_QML}/*/*/*.qml \
    ${OE_QMAKE_PATH_QML}/*/*/*.qmlc \
    ${OE_QMAKE_PATH_QML}/*/*/*.js \
    ${OE_QMAKE_PATH_QML}/*/*/*.jsc \
    ${OE_QMAKE_PATH_QML}/*/*/*.png \
    ${OE_QMAKE_PATH_QML}/*/*/*/*${SOLIBSDEV} \
    ${OE_QMAKE_PATH_QML}/*/*/*/qmldir \
    ${OE_QMAKE_PATH_QML}/*/*/*/images \
    ${OE_QMAKE_PATH_QML}/*/*/*/*.qmltypes \
    ${OE_QMAKE_PATH_QML}/*/*/*/*.qml \
    ${OE_QMAKE_PATH_QML}/*/*/*/*.qmlc \
    ${OE_QMAKE_PATH_QML}/*/*/*/*.js \
    ${OE_QMAKE_PATH_QML}/*/*/*/*.jsc \
    ${OE_QMAKE_PATH_QML}/*/*/*/*.png \
    ${OE_QMAKE_PATH_QML}/*/*/*/*.metainfo \
    ${OE_QMAKE_PATH_QML}/*/*/*/*/*${SOLIBSDEV} \
    ${OE_QMAKE_PATH_QML}/*/*/*/*/qmldir \
    ${OE_QMAKE_PATH_QML}/*/*/*/*/*.qmltypes \
    ${OE_QMAKE_PATH_QML}/*/*/*/*/*.qml \
    ${OE_QMAKE_PATH_QML}/*/*/*/*/*.qmlc \
    ${OE_QMAKE_PATH_QML}/*/*/*/*/*.js \
    ${OE_QMAKE_PATH_QML}/*/*/*/*/*.jsc \
    ${OE_QMAKE_PATH_QML}/*/*/*/*/*/*.png \
"

FILES_${PN}-tools = " \
    ${OE_QMAKE_PATH_BINS}/* \
"
FILES_${PN}-plugins = " \
    ${OE_QMAKE_PATH_PLUGINS}/*/*${SOLIBSDEV} \
    ${OE_QMAKE_PATH_PLUGINS}/*/*/*${SOLIBSDEV} \
    ${OE_QMAKE_PATH_PLUGINS}/*/*/*/*${SOLIBSDEV} \
"
FILES_${PN}-mkspecs = "\
    ${OE_QMAKE_PATH_ARCHDATA}/mkspecs \
"

# modifications to normal packages
FILES_${PN} += " \
    ${OE_QMAKE_PATH_LIBS}/lib*${SOLIBS} \
    ${OE_QMAKE_PATH_LIBEXECS} \
"
FILES_${PN}-dev += " \
    ${OE_QMAKE_PATH_LIBS}/lib*${SOLIBSDEV} \
    ${OE_QMAKE_PATH_LIBS}/pkgconfig \
    ${OE_QMAKE_PATH_LIBS}/cmake/* \
    ${OE_QMAKE_PATH_LIBS}/*.prl \
    ${OE_QMAKE_PATH_LIBS}/*.la \
    ${OE_QMAKE_PATH_HEADERS}/* \
    /usr/modules \
    /usr/doc \
"
FILES_${PN}-staticdev += " \
    ${OE_QMAKE_PATH_LIBS}/*.a \
    ${OE_QMAKE_PATH_PLUGINS}/*/*.a \
    ${OE_QMAKE_PATH_PLUGINS}/*/*.prl \
    ${OE_QMAKE_PATH_PLUGINS}/*/*/*.a \
    ${OE_QMAKE_PATH_PLUGINS}/*/*/*.prl \
    ${OE_QMAKE_PATH_QML}/*/*.a \
    ${OE_QMAKE_PATH_QML}/*/*.prl \
    ${OE_QMAKE_PATH_QML}/*/*/*.a \
    ${OE_QMAKE_PATH_QML}/*/*/*.prl \
    ${OE_QMAKE_PATH_QML}/*/*/*/*.a \
    ${OE_QMAKE_PATH_QML}/*/*/*/*.prl \
    ${OE_QMAKE_PATH_QML}/*/*/*/*/*.a \
    ${OE_QMAKE_PATH_QML}/*/*/*/*/*.prl \
"
FILES_${PN}-examples = " \
    ${OE_QMAKE_PATH_EXAMPLES} \
"
