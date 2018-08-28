package vaxsoft.com.vaxphone.MainDrawer.MainDrawerStatus;

public class StatusData
{
    private String m_sStatus;
    Integer m_sStatusIcon;

    public StatusData(String sStatus, Integer sStatusIcon)
    {
        this.m_sStatus = sStatus;
        this.m_sStatusIcon = sStatusIcon;
    }

    String GetStatusText()
    {
        return m_sStatus;
    }

    public Integer GetStatusIcon()
    {
        return m_sStatusIcon;
    }
}
