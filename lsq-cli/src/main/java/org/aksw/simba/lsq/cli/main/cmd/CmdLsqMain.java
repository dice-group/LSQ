package org.aksw.simba.lsq.cli.main.cmd;



import picocli.CommandLine.Command;
import picocli.CommandLine.Option;


@Command(name="lsq", description = "LSQ Subcommands", subcommands = {
        CmdLsqAnalyze.class,
        CmdLsqRdfize.class,
        CmdLsqBenchmarkMain.class,
        CmdLsqProbe.class
})
public class CmdLsqMain {
    @Option(names={"-h", "--help"}, usageHelp = true, description = "Show general help")
    public boolean help = false;

}
