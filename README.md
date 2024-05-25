# NaN Health Fix: Fabric

A simple port of @Kasualix 's [NaNHealthFixer Mod](https://github.com/MCTeamPotato/NaNHealthFixer) for Fabric, 
converting all event calls into Mixins.

The mod hooks into multiple points handling health in LivingEntities & their inheritors, validating their current
health to ensure it's not below 0 or Not-A-Number (NaN). This avoids some weird half-dead behaviour, commonly seen
in older mods.

This mod only officially supports 1.20.1, but may work on both older & newer versions. PRs for official support welcome.