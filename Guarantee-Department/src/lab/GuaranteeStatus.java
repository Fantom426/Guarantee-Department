package lab;

import javax.swing.*;


public enum GuaranteeStatus {
    Repaired {
        @Override
        public String value() {
            return "R";
        }

        @Override
        public String toString() {
            return "Ремонт завершен";
        }
    },
    Process {
        @Override
        public String value() {
            return "P";
        }

        @Override
        public String toString() {
            return "Товар принят";
        }
    },
    Repair_impossible {
        @Override
        public String value() {
            return "T";
        }

        @Override
        public String toString() {
            return "Ремонт невозможен";
        }
    },
    Not_guarantee {
        @Override
        public String value() {
            return "N";
        }

        @Override
        public String toString() {
            return "Не гарантийный случай";
        }
    };

    public abstract String value();

    public static GuaranteeStatus fromValue(String value) {
        if (Repaired.value().equals(value)) {
            return Repaired;
        } else if (Process.value().equals(value)) {
            return Process;
        } else if (Repair_impossible.value().equals(value)) {
            return Repair_impossible;
        } else if (Not_guarantee.value().equals(value)) {
            return Not_guarantee;
        }    else throw new IllegalArgumentException("value " + value + " is unsupported");
    }
}
