package com.lizi.datastructure.symboltable;

import com.lizi.datastructure.Queue;

public class ThreeST<Value> {
	private Node<Value> root;
	private static class Node<Value>{
		char c;
		Node<Value> left,mid,right;
		Value value;
	}
	public boolean contains(String key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to contains() is null");
        }
        return get(key) != null;
    }
	public Value get(String key){
		Node<Value> x=get(root,key,0);
		if(x==null) return null;
		return (Value)x.value;
	}
	
	private Node<Value> get(Node<Value> node, String key, int figure) {
		if(node==null) return null;
		char c=key.charAt(figure);
		if(c<node.c) return get(node.left, key, figure);
		else if(c>node.c) return get(node.right, key, figure);
		else if(figure<key.length()-1)
			return get(node.mid, key, figure+1);
		else return node;
	}
	
	public void put(String key,Value value){
		root=put(root, key,value,0);
	}

	private Node<Value> put(Node<Value> node, String key, Value value, int figure) {
		char c=key.charAt(figure);
		if(node==null){
			node=new Node<Value>();
			node.c=c;
		}
		if(c<node.c) 		node.left=put(node.left, key, value, figure);
		else if(c>node.c)   node.left=put(node.right, key, value, figure);
		else if(figure<key.length()-1) 
			                node.mid=put(node.mid, key, value, figure+1);
		else                node.value=value;
		return node;
	}
	
	public String longestPrefixOf(String query) {
        if (query == null) {
            throw new IllegalArgumentException("calls longestPrefixOf() with null argument");
        }
        if (query.length() == 0) return null;
        int length = 0;
        Node<Value> x = root;
        int i = 0;
        while (x != null && i < query.length()) {
            char c = query.charAt(i);
            if      (c < x.c) x = x.left;
            else if (c > x.c) x = x.right;
            else {
                i++;
                if (x.value != null) length = i;
                x = x.mid;
            }
        }
        return query.substring(0, length);
    }

    public Iterable<String> keys() {
        Queue<String> queue = new Queue<String>();
        collect(root, new StringBuilder(), queue);
        return queue;
    }

    public Iterable<String> keysWithPrefix(String prefix) {
        if (prefix == null) {
            throw new IllegalArgumentException("calls keysWithPrefix() with null argument");
        }
        Queue<String> queue = new Queue<String>();
        Node<Value> x = get(root, prefix, 0);
        if (x == null) return queue;
        if (x.value != null) queue.enqueue(prefix);
        collect(x.mid, new StringBuilder(prefix), queue);
        return queue;
    }

    private void collect(Node<Value> x, StringBuilder prefix, Queue<String> queue) {
        if (x == null) return;
        collect(x.left,  prefix, queue);
        if (x.value != null) queue.enqueue(prefix.toString() + x.c);
        collect(x.mid,   prefix.append(x.c), queue);
        prefix.deleteCharAt(prefix.length() - 1);
        collect(x.right, prefix, queue);
    }

    public Iterable<String> keysThatMatch(String pattern) {
        Queue<String> queue = new Queue<String>();
        collect(root, new StringBuilder(), 0, pattern, queue);
        return queue;
    }
 
    private void collect(Node<Value> x, StringBuilder prefix, int i, String pattern, Queue<String> queue) {
        if (x == null) return;
        char c = pattern.charAt(i);
        if (c == '.' || c < x.c) collect(x.left, prefix, i, pattern, queue);
        if (c == '.' || c == x.c) {
            if (i == pattern.length() - 1 && x.value != null) queue.enqueue(prefix.toString() + x.c);
            if (i < pattern.length() - 1) {
                collect(x.mid, prefix.append(x.c), i+1, pattern, queue);
                prefix.deleteCharAt(prefix.length() - 1);
            }
        }
        if (c == '.' || c > x.c) collect(x.right, prefix, i, pattern, queue);
    }
}
