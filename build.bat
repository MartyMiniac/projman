javac projman/Main.java
jar cvfe projman.jar ^
projman.Main projman/*.class ^
projman/stuctures/*.class ^
projman/helpers/*.class ^
projman/controllers/*.class ^
projman/commands/*.class
call clean.bat