python do_srcrev_update() {
    import subprocess

    recipe = d.getVar("FILE")

    scms = []
    fetcher = bb.fetch2.Fetch(d.getVar('SRC_URI').split(), d)
    urldata = fetcher.ud
    for u in urldata:
        if urldata[u].method.supports_srcrev():
            scms.append(u)

    if len(scms) == 0:
        return

    if len(scms) == 1 and len(urldata[scms[0]].names) == 1:
        ud = urldata[scms[0]]
        rev = ud.method.latest_revision(ud, d, ud.names[0])
        srcrev = d.getVar("SRCREV")

        if srcrev == rev:
            bb.plain("%s SRCREV: %s is already latest" % (recipe, srcrev))
            return

        bb.plain("%s SRCREV: %s -> %s" % (recipe, srcrev, rev))
        cmd = "sed -i %s -e 's/%s/%s/'" % (recipe, srcrev, rev)
        bb.process.run(cmd, log=None, shell=True, stderr=subprocess.PIPE, cwd=None)
        return

    for scm in scms:
        ud = urldata[scm]
        for name in ud.names:
            rev = ud.method.latest_revision(ud, d, name)
            srcrev = d.getVar("SRCREV_%s" % name)

            if srcrev == rev:
                bb.plain("%s SRCREV_%s: %s is already latest" % (recipe, name, srcrev))
                continue

            bb.plain("%s SRCREV_%s: %s -> %s" % (recipe, name, srcrev, rev))
            cmd = "sed -i %s -e 's/%s/%s/'" % (recipe, srcrev, rev)
            bb.process.run(cmd, log=None, shell=True, stderr=subprocess.PIPE, cwd=None)
}
do_srcrev_update[nostamp] = "1"
addtask srcrev_update after do_fetch
