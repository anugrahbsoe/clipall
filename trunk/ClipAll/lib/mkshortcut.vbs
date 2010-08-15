Set Shell = CreateObject("WScript.Shell")
DesktopPath = Shell.SpecialFolders("Desktop")
Set link = Shell.CreateShortcut(DesktopPath & "\ClipAll.lnk")
link.Arguments = "Arg1 Arg2 Arg3"
link.Description = "A Tooltip"
link.HotKey = "CTRL+ALT+SHIFT+F"
link.IconLocation = "$INSTALL_PATH\resources\console.ico"
link.TargetPath = "$INSTALL_PATH\dist\ClipAll.jar"
link.WindowStyle = 1
link.WorkingDirectory = "$INSTALL_PATH"
link.Save