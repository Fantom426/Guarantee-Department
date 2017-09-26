package lab;


public enum View {
    Agents {
        @Override
        public String getPath() {
            return "/view/agents.fxml";
        }
    },
    guarantees {
        @Override
        public String getPath() {
            return "/view/guarantees.fxml";
        }
    },
    NewGuarantee {
        @Override
        public String getPath() {
            return "/view/new_Guarantee.fxml";
        }
    },
    Home {
        @Override
        public String getPath() {
            return "/view/home.fxml";
        }
    },
    Main {
        @Override
        public String getPath() {
            return "/view/main.fxml";
        }
    },
    Goods {
        @Override
        public String getPath() {
            return "/view/goods.fxml";
        }
    };

    public abstract String getPath();
}
