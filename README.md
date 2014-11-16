BruteForceProtection
====================
Known Issues:

A timing attack is possible. The system calling this library should take into account the timing differences between a successful
result from this library and an unsuccessful result. If the calling system performs a database lookup on a valid result, this could 
take longer then denying access with an invalid result

Run requirements:

To run you will probably need to increase the maximum memory based on expected use. The java parameter -Xmx from https://docs.oracle.com/javase/7/docs/technotes/tools/solaris/java.html .
Without this increase if you surpass the Java default value on your machine, this program will crash. Sample to allot 8GB: -Xmx8192M . If you are running in eclipse, you can add this
under Run->Run Configurations->Arguments->VM