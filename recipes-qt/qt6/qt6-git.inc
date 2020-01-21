QT6_MODULE ?= "${BPN}"
QT6_MODULE_BRANCH ?= "wip/cmake"
QT6_MODULE_BRANCH_PARAM ?= "branch=${QT6_MODULE_BRANCH};nobranch=1"

SRC_URI = "${QT6_GIT}/${QT6_GIT_PROJECT}/${QT6_MODULE}.git;name=${QT6_MODULE};${QT6_MODULE_BRANCH_PARAM};protocol=${QT6_GIT_PROTOCOL}"

DESCRIPTION ?= "Qt is a cross-platform application development framework for desktop, embedded and mobile."
HOMEPAGE ?= "https://www.qt.io"

CVE_PRODUCT = "qt"

S = "${WORKDIR}/git"

PV = "6.0.0"
