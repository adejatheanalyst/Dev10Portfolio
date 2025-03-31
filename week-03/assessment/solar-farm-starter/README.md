
# Solar Farm

## TODO

* [x] Define the models
  * [x] Define the `SolarPanel` class
  * [x] Define the `Material` enumeration
* [x] Define the data layer
  * [x] Define the `DataAccessException` class
  * [x] Define the `SolarPanelRepository` interface
  * [x] Define the `SolarPanelFileRepository` class
  * [x] Create seed data
  * [x] Test the repository
* [x] Define the domain layer
  * [x] Define the `SolarPanelService` class
  * [x] Test the service
* [x] Define the UI layer
  * [x] Define the `TextIO` interface
  * [x] Define the `ConsoleIO` class
  * [x] Define the `View` class
  * [x] Define the `Controller` class
* [x] Define the `App` class

## High Level Requirements

The user is a solar farm administrator.

- Add a solar panel to the farm.
- Update a solar panel.
- Remove a solar panel.
- Display all solar panels in a section.

## Background

Solar panels are arranged in sections, rows, and columns. A panel can be uniquely identified with those three things.

## Panels

The application is only required to track solar panels. The concept of **sections** is not a separate class. It is a field in the solar panel class.

### Data

- **Section**: name that identifies where the panel is installed.
- **Row**: the row number in the section where the panel is installed.
- **Column**: the column number in the section where the panel is installed.
- **Year Installed**
- **Material**: multicrystalline silicon, monocrystalline silicon, amorphous silicon, cadmium telluride, or copper indium gallium selenide.
- **Is Tracking**: determines if the panel is installed with sun-tracking hardware.

Panels can be uniquely identified by section, row, and column.

### Rules

- **Section** is required and cannot be blank.
- **Row** is a positive number less than or equal to 250.
- **Column** is a positive number less than or equal to 250.
- **Year Installed** must be in the past.
- **Material** is required and can only be one of the five materials listed.
- **Is Tracking** is required.
- The combined values of **Section**, **Row**, and **Column** may not be duplicated.

## Sample UI

### Start Up / Main Menu

```
Welcome to Solar Farm
=====================

Main Menu
=========
0. Exit
1. Find Panels by Section
2. Add a Panel
3. Update a Panel
4. Remove a Panel
Select [0-4]:
```

### Find Panels by Section

```
Find Panels by Section
======================

Section Name: The Ridge

Panels in The Ridge
Row Col Year Material Tracking
  1   1 2014     CIGS      yes
  1   2 2014     GIGS      yes
  1   3 2015     GIGS      yes
  2   1 2018   PolySi       no
  2   3 2018   PolySi       no
```

### Add a Panel

```
Add a Panel
===========

Section: Flats
Row: 251
[Err]
Row must be between 1 and 250.
Row: 250
Column: 43
Material: CdTe
Installation Year: 2020
Tracked [y/n]: n

[Success] 
Panel Flats-250-43 added.
```

### Update a Panel

Section, Row, or Column are not required to be updatable, but editing of other fields is required.

```
Update a Panel
==============

Section: Treeline
Row: 10
Column: 5

Editing Treeline-10-5
Press [Enter] to keep original value.

Section (Treeline):
Row (10): 11
Column (5):
Material (CdTe):
Installation Year (2020):
Tracked (no) [y/n]: y

[Success] 
Panel Treeline-11-5 updated.
```

### Remove a Panel - Success

```
Remove a Panel
==============

Section: Flats
Row: 50
Column: 50

[Success] 
Panel Flats-50-50 removed.
```

### Remove a Panel - Failure

```
Remove a Panel
==============

Section: Flats
Row: 20
Column: 21

[Err] 
There is no panel Flats-20-21.
```

## Technical Requirements

Use a three-layer architecture.

Data must be stored in a delimited file. Stopping and starting the application should not change the underlying data. The application picks up where it left off.

Repositories should throw a custom exception, never file-specific exceptions.

Repository and service classes must be fully tested with both negative and positive cases. Do not use your "production" data file to test your repository.

Solar panel material should be a Java enum with five values. Since solar technology is changing quickly, an enum may be a risky choice. The requirement is included specifically to practice with enums.
