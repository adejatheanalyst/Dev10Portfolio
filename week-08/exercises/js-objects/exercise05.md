# Exercise: Objects - Exercise 05

## OPTIONAL CHALLENGE: PATH FINDING

### Overview

Imagine the following string represents a 2D terrain.

```js
const terrain = `
#############
#
#    ##
# @ #     X
############
`;
```

* `#` are obstacles
* `@` is an agent (hero, robot, unicorn, whatever you like).
* `X` is the goal (treasure, battery, cotton candy).

Given a 2D terrain string, return a 2D terrain string that shows the path the agent traveled to get to the goal. `P` is a path.

For example, one path for the terrain above is:

```
#############
# PPPPPPP
# P  ## PPP
# P #     P
############
```

Diagonal moves are not allowed, only up, down, left, or right.

### More examples:

Example:

```

@
            X
```

Solution:

```

PPPPPPPPPPPPP
            P
```

Example:

```
    #    X
@   #
    ########
#
```

Solution:

```
    #    PP
PP  #     PPP
 P  ########P
#PPPPPPPPPPPP
```

Example:

```
@#
#

           X
```

Solution:

```
No solution. Can't reach the goal.
```

### Instructions

Write a function that accepts a terrain string and returns a solved path terrain string.
function solveTerrain(terrain: string): string

You'll likely want more than one function.
