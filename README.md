BruteForceProtection
====================
Known Issues:

A timing attack is possible. The system calling this library should take into account the timing differences between a successful
result from this library and an unsuccessful result. If the calling system performs a database lookup on a valid result, this could 
take longer then denying access with an invalid result

Run requirements:

To run you will probably need to increase the maximum memory based on expected use. The java parameter -Xmx from https://docs.oracle.com/javase/7/docs/technotes/tools/solaris/java.html .
Without this increase if you surpass the Java default value on your machine, this program will crash. Recommended to allot 8GB: -Xmx8192M . 

If you are running in eclipse, you can add this under Run->Run Configurations->Arguments->VM. 

This also applies for when you run the Junit tests.

Summary Presentation:

http://prezi.com/eim30me8emg5/?utm_campaign=share&utm_medium=copy&rc=ex0share


FAQ:

Q. The tests aren't finishing, its running out of memory?!
A. Check that you are running with the -Xmx option for the Java VM. 8GB is recommended minimum for the tests and running.

Q. Is there a memory leak? There are no more requests to the service and it doesn't look like the memory is being freed.
A. There is no memory leak, this is as "designed". The cleanup on the values is still happening, but the Java object HashMap reserves the memory as it grows. This could be changed to a different data structure at a later date if needed.

