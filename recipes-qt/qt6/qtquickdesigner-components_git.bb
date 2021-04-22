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

SRCREV = "4e5ecf812bc8019ddf851f3603c65db76b2b5b9c"
