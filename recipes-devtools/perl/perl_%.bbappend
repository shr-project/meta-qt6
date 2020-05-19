do_install_append_class-nativesdk () {
    sed -i -e 's|$OECORE_NATIVE_SYSROOT/|${SDKPATHNATIVE}|g' ${D}${bindir}/perl
}
