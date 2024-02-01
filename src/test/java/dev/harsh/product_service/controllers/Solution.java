package dev.harsh.product_service.controllers;

import java.util.*;

class Solution {

    public static class Pair{
        private final int value;
        private final int index;
        private final boolean isMin;

        public Pair(int value, int index,boolean isMin) {
            this.value = value;
            this.index = index;
            this.isMin = isMin;
        }
    }
    public int[] restoreArray(int[][] adjacentPairs) {
        int n = adjacentPairs.length;
        int [] output = new int[n+1];
        //first we will build a graph
        Map<Integer,List<Integer>> adjList = buildGraph(adjacentPairs);
        //after we get a graph we can calculate the indegree and filter with indegree 1
        List<Integer> oneIndegree = getSingleDegreeItems(adjList);
        if(oneIndegree.size() != 2){
            return output;
        }
        //After we go indegree we can start with the indegree 1 and do bfs and fill array
        fillOutputWithBfs(oneIndegree,adjList,output);

        return output;
    }

    private void fillOutputWithBfs(List<Integer> oneIndegree, Map<Integer, List<Integer>> adjList, int[] output) {
        Queue<Pair> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        Pair p1 = new Pair(oneIndegree.get(0),0,true);
        Pair p2 = new Pair(oneIndegree.get(1),output.length - 1,false);
        queue.add(p1); queue.add(p2);
        visited.add(p1.value); visited.add(p2.value);
        while(!queue.isEmpty()){
            Pair p = queue.poll();
            output[p.index] = p.value;
            for(int u : adjList.get(p.value)){
                if(!visited.contains(u)){
                    if(p.isMin){
                        queue.add(new Pair(u,p.index + 1,true));
                    }
                    else{
                        queue.add(new Pair(u,p.index - 1,false));
                    }
                   visited.add(u);
                }
            }
        }
    }

    private List<Integer> getSingleDegreeItems(Map<Integer, List<Integer>> adjList) {
        return adjList.keySet().stream().filter((l)->adjList.get(l).size() == 1).toList();
    }

    private Map<Integer,List<Integer>> buildGraph(int[][] adjacentPairs) {
        Map<Integer,List<Integer>> adjList = new HashMap<>();
        for(int [] pairs : adjacentPairs){
            int u = pairs[0],v = pairs[1];
            adjList.putIfAbsent(u,new ArrayList<>());
            adjList.get(u).add(v);
            adjList.putIfAbsent(v,new ArrayList<>());
            adjList.get(v).add(u);
        }
        return adjList;
    }

}