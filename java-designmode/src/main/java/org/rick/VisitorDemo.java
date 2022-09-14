package org.rick;

public class VisitorDemo {
    public static void main(String[] args) {
        ComputerPart computer = new Computer();
        computer.accept(new ComputerPartDisplayVisitor());
    }
}

interface ComputerPartVisitor {
    public void visit(Computer computer);
    public void visit(Mouse mouse);
    public void visit(Keyboard keyboard);
    public void visit(Monitor monitor);
}

interface ComputerPart {
    public default void accept(ComputerPartVisitor computerPartVisitor) {
        if (this instanceof Computer) {
            computerPartVisitor.visit((Computer) this);
        } else if (this instanceof Mouse) {
            computerPartVisitor.visit((Mouse) this);
        } else if (this instanceof Keyboard) {
            computerPartVisitor.visit((Keyboard) this);
        } else if (this instanceof Monitor) {
            computerPartVisitor.visit((Monitor) this);
        } else {
            throw new RuntimeException("not support to visit such part");
        }
    }
}

class Keyboard  implements ComputerPart {
}

class Monitor  implements ComputerPart {
}

class Mouse  implements ComputerPart {
}

class Computer implements ComputerPart {

    ComputerPart[] parts;

    public Computer(){
        parts = new ComputerPart[] {new Mouse(), new Keyboard(), new Monitor()};
    }

    @Override
    public void accept(ComputerPartVisitor computerPartVisitor) {
        for (ComputerPart part : parts) {
            part.accept(computerPartVisitor);
        }
        computerPartVisitor.visit(this);
    }
}

class ComputerPartDisplayVisitor implements ComputerPartVisitor {

    @Override
    public void visit(Computer computer) {
        System.out.println("Displaying Computer.");
    }

    @Override
    public void visit(Mouse mouse) {
        System.out.println("Displaying Mouse.");
    }

    @Override
    public void visit(Keyboard keyboard) {
        System.out.println("Displaying Keyboard.");
    }

    @Override
    public void visit(Monitor monitor) {
        System.out.println("Displaying Monitor.");
    }
}

