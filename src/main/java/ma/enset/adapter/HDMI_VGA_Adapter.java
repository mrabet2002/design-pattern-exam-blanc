package ma.enset.adapter;


import ma.enset.entities.Agent;
import ma.enset.entities.IAgent;

public class HDMI_VGA_Adapter implements HDMI {
    private VGA vga;

    public HDMI_VGA_Adapter(VGA vga) {
        this.vga = vga;
    }

    public void connect(VGA vga) {
        this.vga = vga;
    }

    public void streamData(IAgent agent) {
        System.out.println("===========================================");
        vga.streamData(agent.toString());
        System.out.println("===========================================");
    }
}
