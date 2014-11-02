BruteForceProtection
====================
Known Issues:
A timing attack is possible. The system calling this library should take into account the timing differences between a successful
result from this library and an unsuccessful result. If the calling system performs a database lookup on a valid result, this could 
take longer then denying access with an invalid result
