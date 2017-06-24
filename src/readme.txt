**************************************************************************************************************************
** Binex Core2d Engine ***************************************************************************************************
**************************************************************************************************************************

Current Version: 1.2

**************************************************************************************************************************

Change Log - 1.2 The Audio Update (From 1.1 to 1.2):

Major Changes:
- Significantly updated AudioHandler.
	- Added CoreSound class to handle audio clips.
	- Current AudioHandler features:
		- Play a sound
		- Loop a sound
		- Stop a sound (new)
		- Pause a sound (new)
		- Resume a paused sound (new)
Minor Changes:
- Removed playSound() calls on buttons in Catalog because they shouldn't be there as they're too project specific.
- Added accessors to nextBtn and prevBtn in Catalog.
	
**************************************************************************************************************************

Change Log - 1.1 The Core Update (From 1.0 to 1.1):

Major Changes:
- The Game class is now called Core.
- All game related naming was changed to either Core or project.
- Naming and terminology changed to make more sense/flow better.
- Finished complete/proper java doc commenting on every class.
- Updated some minor things in WorldHandler to tick/render the world properly depending on tile size.
- Added methods for interacting with the world.  For example:
	- getChunkWithTileAtCoords
	- getChunkWithTile
	- getChunkLayer
	- getTileAtCoords

**************************************************************************************************************************

Known Bugs:
- Looped sounds cannot be paused and resumed after the first play through. (1.2)
- Volume control doesn't work for looped sounds. (1.2)

**************************************************************************************************************************