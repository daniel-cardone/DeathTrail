# DeathTrails
A Spigot plugin for Minecraft 1.16. It contains a three commands to create and customize a trail of holes that will follow the player.

## Commands
### /trail \<player>
###### (Permission: `deathtrail.trailtype`)
Toggle a player's death trail on/off (ex. `/trail Notch`)

### /trailtype \<wide/hole/cube/void>
###### (Permission: deathtrail.trailtype)
Specify how the trails will behave (ex. `/trailtype hole`)
#### Types:
##### Wide:
Creates a 5x5 hole, 1 block deep
##### Hole:
Creates a 1x1 hole, 5 blocks deep
##### Cube:
Creates a 3x3 hole, 3 blocks deep
##### Void:
Creates a 1x1 hole, down to the void

### /trailspeed \<ticks>
###### (Permission: `deathtrail.trailspeed`)
Specify how frequently a the trails appear (20 ticks = 1 second) (ex. `/trailspeed 20`)
