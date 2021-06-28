LICENSE = "GPL-3.0 | The-Qt-Company-Commercial"
LIC_FILES_CHKSUM = " \
    file://LICENSE.GPL3;md5=d32239bcb673463ab874e80d47fae504 \
"

inherit qt6-qmake

include recipes-qt/qt6/qt6-git.inc
include recipes-qt/qt6/qt6.inc

QT_GIT_PROJECT = "qt-labs"
QT_MODULE_BRANCH = "dev"

DEPENDS += "qtbase qtdeclarative qtquickcontrols2"

SRCREV = "6bb7c0f52c7f4d355abc09c955f6b6b0ee66b98a"
