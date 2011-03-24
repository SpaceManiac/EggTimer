== ABOUT EGGTIMER ==

EggTimer is a small Bukkit plugin to allow the creation of timed item dropoffs
in chests around the map, for things like providing extra resources to factions
who can hold a control point, allow easy grabbing of items on a creative server,
loot in dungeons, and other situations.

  - Chests are only updated if the chunk containing them is loaded, and are
    cleared before items are added to prevent buildup of large amounts of items
    over time.
  - Items can be added in any amount, including amounts greater than 64 (in
    which case the items will spread over multiple slots).
  - Metadata for items can also be selected. For tools, this is the damage
    value, and for other items this is a value from 0-16 with meaning varying
    by type (most items should be left at 0).
  - Multiworld support and item spawn intervals configurable down to the second.

EggTimer's configuration is stored in plugins/EggTimer/locations.txt. Each line
in this file can either start with a '#' to be a comment, or follow this format:

    world,x,y,z=item,count,data,interval

Where world is the name of the world, x, y and z are block coordinates, item is
the id of the item, count is the amount, data is the metadata value, and
interval is the number of seconds between the respawn of the item. There is not
yet a way to add multiple item stacks to the same chest (a later entry for the
same location as a previous one will override it).

EggTimer is currently on version 1.0.

== LICENSE ==

Copyright (C) 2011 by Tad Hardesty

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
