OpenTriad-CONTRIBUTOR-README
=========

This file is intended to assist new developers within the project. It's a mere simple documentation
for aspects of OpenTriad and how problems were solved while development. This readme also contains
guidelines which must be used when contributing to this project.



Plain development rules
=========

CamelCase for the win!

Android best practices
    - No hardcoded string variables for view-elements.
    - Make use of shared view-styles, refactor views whenever possible.
    - Be aware of memory usage and look out for context leaks!

Format your code
    The default formatting of android studio is the project default.

Public member variables
    We're on a embedded platform, so don't waste cpu-cycles with virtual getter-&setter-methods.

A brief description for each class and non-lifecycle-methods
    We want to keep this project simple, flexible and maintainable. Take a look at the classes to
    get an idea how class headers are structured. It's unnecessary to document lifecycle-methods,
    that's whats the android documentation is for.

Git-flow
    Keep your work separate from the development branch and use feature-branches instead.

Music
    It is important to listen to good music while contributing to this project.



Problems during development and its solutions
=========

Translating two-dimensional- into one-dimensional-coordinates.

    Because of its simplicity and the speed, I've decided to use a one dimensional array / list for the
    Cardviews on the Battlefield, BattlefieldView and the DeckView instead of two dimensions arrays.

    Convert from 2d (row, pos) to 1d (when setting a card on the battlefield for example):
    cards[(row * OpenTriad.BATTLEFIELD_MAX_ROW) + pos] = card;