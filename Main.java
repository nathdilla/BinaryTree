class Main {
  public static void main(String[] args) {
    BinaryTree tree = new BinaryTree();

    tree.addNode(232,"God");
    tree.addNode(23,"Adam");
    tree.addNode(65,"Eve");
    tree.addNode(111,"Cain");
    tree.addNode(145,"Abel");
    tree.addNode(200,"Noah");

    System.out.println("IN ORDER TRAVERSAL: ");
    tree.inOrderTraverseTree(tree.root);

    System.out.println("PRE ORDER TRAVERSAL: ");
    tree.preorderTraverseTree(tree.root);

    System.out.println("POST ORDER TRAVERSAL: ");
    tree.postOrderTraverseTree(tree.root);

    System.out.println("Search for 65");
    System.out.println(tree.findNode(65));

    System.out.println("REMOVE KEY 23");
    tree.remove(23);
    tree.preorderTraverseTree(tree.root);
  }
}