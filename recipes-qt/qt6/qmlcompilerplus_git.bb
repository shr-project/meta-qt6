LICENSE = "The-Qt-Company-Commercial"
LIC_FILES_CHKSUM = " \
    file://src/qmlcompilerplus/cppcodegen_p.h;endline=27;md5=35a959124a01f6c4cd862f7953372cba \
"

inherit qt6-cmake

include recipes-qt/qt6/qt6-git.inc
include recipes-qt/qt6/qt6.inc

python() {
    if d.getVar('QT_EDITION') != 'commercial':
        raise bb.parse.SkipRecipe('Available only with Commercial Qt')
}

QT_GIT = "git://codereview.qt-project.org"
QT_GIT_PROTOCOL = "ssh"
QT_MODULE = "tqtc-qmlcompilerplus"

DEPENDS += "qtbase qtdeclarative qtdeclarative-native"

PTEST_ENABLED = "0"

BBCLASSEXTEND = "native nativesdk"
