PACKAGECONFIG[examples] = "-DBUILD_EXAMPLES=ON,-DBUILD_EXAMPLES=OFF,"
PACKAGECONFIG[tests] = "-DBUILD_TESTING=ON,-DBUILD_TESTING=OFF,"
PACKAGECONFIG[translations] = ",,qttranslations,,qttranslations-${BPN}"
#PACKAGECONFIG[qtquickcompiler] = "-Dqtquickcompiler=ON,-Dqtquickcompiler=OFF,qtdeclarative-native"

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

PACKAGES =. "${PN}-qmldesigner ${PN}-qmlplugins ${PN}-tools ${PN}-plugins ${PN}-examples "

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
    ${PN}-qmldesigner \
    "

# extra packages
FILES_${PN}-qmldesigner += " \
    ${QT6_INSTALL_QMLDIR}/*/designer \
    ${QT6_INSTALL_QMLDIR}/*/*/designer \
    ${QT6_INSTALL_QMLDIR}/*/*/*/designer \
"

# qmlplugins 1-4 levels of subdirs qmldir + *{.so,*.qmltypes,*.qml,*.qmlc,*.js,*.jsc}
FILES_${PN}-qmlplugins = " \
    ${QT6_INSTALL_QMLDIR}/*.qmltypes \
    ${QT6_INSTALL_QMLDIR}/*/*${SOLIBSDEV} \
    ${QT6_INSTALL_QMLDIR}/*/qmldir \
    ${QT6_INSTALL_QMLDIR}/*/*.qmltypes \
    ${QT6_INSTALL_QMLDIR}/*/*.qml \
    ${QT6_INSTALL_QMLDIR}/*/*.qmlc \
    ${QT6_INSTALL_QMLDIR}/*/*.js \
    ${QT6_INSTALL_QMLDIR}/*/*.jsc \
    ${QT6_INSTALL_QMLDIR}/*/*.png \
    ${QT6_INSTALL_QMLDIR}/*/*/*${SOLIBSDEV} \
    ${QT6_INSTALL_QMLDIR}/*/*/qmldir \
    ${QT6_INSTALL_QMLDIR}/*/*/*.qmltypes \
    ${QT6_INSTALL_QMLDIR}/*/*/*.qml \
    ${QT6_INSTALL_QMLDIR}/*/*/*.qmlc \
    ${QT6_INSTALL_QMLDIR}/*/*/*.js \
    ${QT6_INSTALL_QMLDIR}/*/*/*.jsc \
    ${QT6_INSTALL_QMLDIR}/*/*/*.png \
    ${QT6_INSTALL_QMLDIR}/*/*/*/*${SOLIBSDEV} \
    ${QT6_INSTALL_QMLDIR}/*/*/*/qmldir \
    ${QT6_INSTALL_QMLDIR}/*/*/*/images \
    ${QT6_INSTALL_QMLDIR}/*/*/*/*.qmltypes \
    ${QT6_INSTALL_QMLDIR}/*/*/*/*.qml \
    ${QT6_INSTALL_QMLDIR}/*/*/*/*.qmlc \
    ${QT6_INSTALL_QMLDIR}/*/*/*/*.js \
    ${QT6_INSTALL_QMLDIR}/*/*/*/*.jsc \
    ${QT6_INSTALL_QMLDIR}/*/*/*/*.png \
    ${QT6_INSTALL_QMLDIR}/*/*/*/*.metainfo \
    ${QT6_INSTALL_QMLDIR}/*/*/*/*/*${SOLIBSDEV} \
    ${QT6_INSTALL_QMLDIR}/*/*/*/*/qmldir \
    ${QT6_INSTALL_QMLDIR}/*/*/*/*/*.qmltypes \
    ${QT6_INSTALL_QMLDIR}/*/*/*/*/*.qml \
    ${QT6_INSTALL_QMLDIR}/*/*/*/*/*.qmlc \
    ${QT6_INSTALL_QMLDIR}/*/*/*/*/*.js \
    ${QT6_INSTALL_QMLDIR}/*/*/*/*/*.jsc \
    ${QT6_INSTALL_QMLDIR}/*/*/*/*/*/*.png \
"

FILES_${PN}-tools = " \
    ${QT6_INSTALL_BINDIR}/* \
"
FILES_${PN}-plugins = " \
    ${QT6_INSTALL_PLUGINSDIR}/*/*${SOLIBSDEV} \
    ${QT6_INSTALL_PLUGINSDIR}/*/*/*${SOLIBSDEV} \
    ${QT6_INSTALL_PLUGINSDIR}/*/*/*/*${SOLIBSDEV} \
"

# modifications to normal packages
FILES_${PN} += " \
    ${QT6_INSTALL_LIBDIR}/lib*${SOLIBS} \
    ${QT6_INSTALL_LIBEXECDIR} \
"
# Note: Not all mkspecs are in [INSTALL_DATADIR]/mkspecs
FILES_${PN}-dev += " \
    ${QT6_INSTALL_LIBDIR}/lib*${SOLIBSDEV} \
    ${QT6_INSTALL_LIBDIR}/pkgconfig \
    ${QT6_INSTALL_LIBDIR}/cmake/* \
    ${QT6_INSTALL_LIBDIR}/*.prl \
    ${QT6_INSTALL_LIBDIR}/*.la \
    ${QT6_INSTALL_LIBDIR}/metatypes \
    ${QT6_INSTALL_INCLUDEDIR}/* \
    ${QT6_INSTALL_DESCRIPTIONSDIR}/* \
    ${QT6_INSTALL_DOCDIR} \
    ${QT6_INSTALL_DATADIR}/mkspecs \
    ${prefix}/mkspecs \
"
FILES_${PN}-staticdev += " \
    ${QT6_INSTALL_LIBDIR}/*.a \
    ${QT6_INSTALL_PLUGINSDIR}/*/*.a \
    ${QT6_INSTALL_PLUGINSDIR}/*/*.prl \
    ${QT6_INSTALL_PLUGINSDIR}/*/*/*.a \
    ${QT6_INSTALL_PLUGINSDIR}/*/*/*.prl \
    ${QT6_INSTALL_QMLDIR}/*/*.a \
    ${QT6_INSTALL_QMLDIR}/*/*.prl \
    ${QT6_INSTALL_QMLDIR}/*/*/*.a \
    ${QT6_INSTALL_QMLDIR}/*/*/*.prl \
    ${QT6_INSTALL_QMLDIR}/*/*/*/*.a \
    ${QT6_INSTALL_QMLDIR}/*/*/*/*.prl \
    ${QT6_INSTALL_QMLDIR}/*/*/*/*/*.a \
    ${QT6_INSTALL_QMLDIR}/*/*/*/*/*.prl \
"

# Note: INSTALL_EXAMPLESDIR is not used
FILES_${PN}-examples = " \
    ${QT6_INSTALL_EXAMPLESDIR} \
    ${prefix}/examples \
"
