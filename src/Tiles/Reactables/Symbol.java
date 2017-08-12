package Tiles.Reactables;

/**
 * These enums represent the symbols that can make up a piece in the game.
 */
public enum Symbol implements SymbolReactable {
    SWORD_VERTICAL {
        @Override
        public Reaction reactWithSword() {
            return Reaction.KILLED;
        }

        @Override
        public Reaction reactWithShield() {
            return Reaction.PUSHED;
        }

        @Override
        public Reaction reactWithNothing() {
            return Reaction.NONE;
        }
    },

    SWORD_HORIZONTAL {
        @Override
        public Reaction reactWithSword() {
            return Reaction.KILLED;
        }

        @Override
        public Reaction reactWithShield() {
            return Reaction.PUSHED;
        }

        @Override
        public Reaction reactWithNothing() {
            return Reaction.NONE;
        }
    },

    SHIELD {
        @Override
        public Reaction reactWithSword() {
            return Reaction.NONE;
        }

        @Override
        public Reaction reactWithShield() {
            return Reaction.NONE;
        }

        @Override
        public Reaction reactWithNothing() {
            return Reaction.NONE;
        }
    },

    NOTHING {
        @Override
        public Reaction reactWithSword() {
            return Reaction.KILLED;
        }

        @Override
        public Reaction reactWithShield() {
            return Reaction.NONE;
        }

        @Override
        public Reaction reactWithNothing() {
            return Reaction.NONE;
        }
    }

}
