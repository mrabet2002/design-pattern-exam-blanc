package ma.enset.adapter;


import ma.enset.entities.Agent;

public class HDMI_VGA_Adapter implements HDMI {
    private VGA vga;

    public HDMI_VGA_Adapter(VGA vga) {
        this.vga = vga;
    }

    public void connect(VGA vga) {
        this.vga = vga;
    }

    public void streamData(Agent agent) {
        vga.streamData(agent.toString());
        System.out.println("======================");
    }
}
