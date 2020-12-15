import javax.lang.model.util.ElementScanner6;

public class BinaryTree{
  Node root;

  public void addNode(int key, String name)
  {
    Node newNode = new Node(key, name);
    if (root == null)
    {
      root = newNode;
    }
    else{
      Node focusNode = root;
      Node parent;

      while(true)
      {
        parent = focusNode;

        if(key <focusNode.key)
        {
          focusNode = focusNode.leftChild;
          if (focusNode == null)
          {
            parent.leftChild = newNode;
            return;
          }
        } else{
          focusNode = focusNode.rightChild;
          if(focusNode == null)
          {
            parent.rightChild = newNode;
            return;
          }
          focusNode.parent = parent;
        }
      }
    }
  }

  public void inOrderTraverseTree(Node focusNode)
  {
    if(focusNode != null)
    {
      inOrderTraverseTree(focusNode.leftChild);
      System.out.println(focusNode.toString());
      inOrderTraverseTree(focusNode.rightChild);
    }
  }

  public void preorderTraverseTree(Node focusNode)
  {
    if(focusNode != null)
    {
      System.out.println(focusNode.toString());
      preorderTraverseTree(focusNode.leftChild);
      preorderTraverseTree(focusNode.rightChild);
    }
  }

  public void postOrderTraverseTree(Node focusNode)
  {
    if(focusNode != null)
    {
      postOrderTraverseTree(focusNode.leftChild);
      postOrderTraverseTree(focusNode.rightChild);
      System.out.println(focusNode.toString());
    }
  }

  public boolean remove(int key)
  {
    Node focusNode = root;
    Node parent = root;

    boolean isItALeftChild = true;

    while(focusNode.key != key)
    {
      parent = focusNode;

      if(key < focusNode.key)
      {
        isItALeftChild = true;
        focusNode = focusNode.leftChild;
      }
      else
      {
        isItALeftChild = false;
        focusNode = focusNode.rightChild;
      }
      if(focusNode == null)
      return false;
    }
    if(focusNode.leftChild == null && focusNode.rightChild == null)
    {
      if(focusNode == root)
      {
        root = null;
      }
      else if(isItALeftChild)
      {
        parent.leftChild = null;
      }
      else
      {
        parent.rightChild = null;
      }
    }
    else if(focusNode.rightChild == null)
    {
      if(focusNode == root)
      root = focusNode.leftChild;
      else if(isItALeftChild)
      parent.leftChild = focusNode.leftChild;
      else
      parent.rightChild = focusNode.leftChild;
    }
    else if(focusNode.leftChild == null)
    {
      if(focusNode == root)
      root = focusNode.rightChild;
      else if(isItALeftChild)
      parent.leftChild = focusNode.rightChild;
      else  
      parent.rightChild = focusNode.leftChild;
    }
    else
    {
      Node replacement = getReplacementNode(focusNode);
      if(focusNode == root)
      {
        root = replacement;
      }
      else if(isItALeftChild)
      parent.leftChild = replacement;
      else
      parent.rightChild = replacement;

      replacement.leftChild = focusNode.leftChild;
    }
    return true;
  }

  public Node getReplacementNode(Node replacedNode)
  {
    Node replacementParent = replacedNode;
    Node replacement = replacedNode;

    Node focusNode = replacedNode.rightChild;

    while(focusNode != null)
    {
      replacementParent = replacement;
      replacement =focusNode;
      focusNode = focusNode.leftChild;
    }
    if(replacement != replacedNode.rightChild)
    {
      replacementParent.leftChild = replacement.rightChild;
      replacement.rightChild = replacedNode.rightChild;
    }

    return replacement;
  }

  public Node findNode(int key)
  {
    Node focusNode = root;

    while(focusNode.key != key)
    {
      if (key < focusNode.key)
      {
        focusNode = focusNode.leftChild;
      }
      else
      {
        focusNode = focusNode.rightChild;
      }
      if (focusNode == null)
      {
        return null;
      }
    }
    return focusNode;
  }

  public Node update(int key, int newKey)
  {
    Node focusNode = findNode(key);
    Node parent = focusNode.parent;
    if (newKey <= parent.key && focusNode == parent.leftChild || 
    newKey >= parent.key && focusNode == parent.rightChild)
    {
      focusNode.key = newKey;
      return focusNode;
    }
    return null;
  }
}

class Node {
  int key;
  String name;

  Node leftChild;
  Node rightChild;
  Node parent;

  Node(int key, String name)
  {
    this.key = key;
    this.name = name;
  }

  public String toString()
  {
    return name + " has key " + key;
  }
}

