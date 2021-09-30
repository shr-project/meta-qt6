LICENSE = "GFDL-1.3 & BSD & ( GPL-3.0 & The-Qt-Company-GPL-Exception-1.0 ) & ( GPL-2.0+ | LGPL-3.0 ) | The-Qt-Company-Commercial"
LIC_FILES_CHKSUM = " \
    file://LICENSE.LGPL3;md5=e6a600fd5e1d9cbde2d983680233ad02 \
    file://LICENSE.GPL2;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://LICENSE.GPL3;md5=d32239bcb673463ab874e80d47fae504 \
    file://LICENSE.GPL3-EXCEPT;md5=763d8c535a234d9a3fb682c7ecb6c073 \
    file://LICENSE.FDL;md5=6d9f2a9af4c8b8c3c769f6cc1b6aaf7e \
"

inherit qt6-cmake

include recipes-qt/qt6/qt6-git.inc
include recipes-qt/qt6/qt6.inc

SRC_URI += " \
    ${QT_GIT}/playground/qlitehtml.git;name=qttools-qlitehtml;branch=master;protocol=${QT_GIT_PROTOCOL};destsuffix=git/src/assistant/qlitehtml \
    git://github.com/litehtml/litehtml.git;name=qttools-qlitehtml-litehtml;branch=master;destsuffix=git/src/assistant/qlitehtml/src/3rdparty/litehtml \
"

DEPENDS += "qtbase qtdeclarative qttools-native"

FILES:${PN}-tools += "${QT6_INSTALL_DATADIR}/phrasebooks"

BBCLASSEXTEND = "native nativesdk"

SRCREV_FORMAT = "qttools_qttools-qlitehtml_qttools-qlitehtml-litehtml"
