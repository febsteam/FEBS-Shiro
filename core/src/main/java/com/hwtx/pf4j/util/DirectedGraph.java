/*
 * Copyright (C) 2012-present the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hwtx.pf4j.util;

import java.util.*;

/**
 * See <a href="https://en.wikipedia.org/wiki/Directed_graph">Wikipedia</a> for more information.
 *
 * @author Decebal Suiu
 */
public class DirectedGraph<V> {

    /**
     * The implementation here is basically an adjacency list, but instead
     * of an array of lists, a Map is used to map each vertex to its list of
     * adjacent vertices.
     */
    private Map<V, List<V>> neighbors = new HashMap<>();

    /**
     * Add a vertex to the graph. Nothing happens if vertex is already in graph.
     */
    public void addVertex(V vertex) {
        if (containsVertex(vertex)) {
            return;
        }

        neighbors.put(vertex, new ArrayList<>());
    }

    /**
     * True if graph contains vertex.
     */
    public boolean containsVertex(V vertex) {
        return neighbors.containsKey(vertex);
    }

    public void removeVertex(V vertex) {
        neighbors.remove(vertex);
    }

    /**
     * Add an edge to the graph; if either vertex does not exist, it's added.
     * This implementation allows the creation of multi-edges and self-loops.
     */
    public void addEdge(V from, V to) {
        addVertex(from);
        addVertex(to);
        neighbors.get(from).add(to);
    }

    /**
     * Remove an edge from the graph. Nothing happens if no such edge.
     * @throws {@link IllegalArgumentException} if either vertex doesn't exist.
     */
    public void removeEdge(V from, V to) {
        if (!containsVertex(from)) {
            throw new IllegalArgumentException("Nonexistent vertex " + from);
        }

        if (!containsVertex(to)) {
            throw new IllegalArgumentException("Nonexistent vertex " + to);
        }

        neighbors.get(from).remove(to);
    }

    public List<V> getNeighbors(V vertex) {
        return containsVertex(vertex) ? neighbors.get(vertex) : new ArrayList<>();
    }

    /**
     * Report (as a Map) the out-degree (the number of tail ends adjacent to a vertex) of each vertex.
     */
    public Map<V, Integer> outDegree() {
        Map<V, Integer> result = new HashMap<>();
        for (V vertex : neighbors.keySet()) {
            result.put(vertex, neighbors.get(vertex).size());
        }

        return result;
    }

    /**
     * Report (as a {@link Map}) the in-degree (the number of head ends adjacent to a vertex) of each vertex.
     */
    public Map<V, Integer> inDegree() {
        Map<V, Integer> result = new HashMap<>();
        for (V vertex : neighbors.keySet()) {
            result.put(vertex, 0); // all in-degrees are 0
        }
        for (V from : neighbors.keySet()) {
            for (V to : neighbors.get(from)) {
                result.put(to, result.get(to) + 1); // increment in-degree
            }
        }

        return result;
    }

    /**
     * Report (as a List) the topological sort of the vertices; null for no such sort.
     * See <a href="https://en.wikipedia.org/wiki/Topological_sorting">this</a> for more information.
     */
    public List<V> topologicalSort() {
        Map<V, Integer> degree = inDegree();

        // determine all vertices with zero in-degree
        Stack<V> zeroVertices = new Stack<>(); // stack as good as any here
        for (V v : degree.keySet()) {
            if (degree.get(v) == 0) {
                zeroVertices.push(v);
            }
        }

        // determine the topological order
        List<V> result = new ArrayList<>();
        while (!zeroVertices.isEmpty()) {
            V vertex = zeroVertices.pop(); // choose a vertex with zero in-degree
            result.add(vertex); // vertex 'v' is next in topological order
            // "remove" vertex 'v' by updating its neighbors
            for (V neighbor : neighbors.get(vertex)) {
                degree.put(neighbor, degree.get(neighbor) - 1);
                // remember any vertices that now have zero in-degree
                if (degree.get(neighbor) == 0) {
                    zeroVertices.push(neighbor);
                }
            }
        }

        // check that we have used the entire graph (if not, there was a cycle)
        if (result.size() != neighbors.size()) {
            return null;
        }

        return result;
    }

    /**
     * Report (as a List) the reverse topological sort of the vertices; null for no such sort.
     */
    public List<V> reverseTopologicalSort() {
        List<V> list = topologicalSort();
        if (list == null) {
            return null;
        }

        Collections.reverse(list);

        return list;
    }

    /**
     * True if graph is a dag (directed acyclic graph).
     */
    public boolean isDag () {
        return topologicalSort() != null;
    }

    /**
     * String representation of graph.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (V vertex : neighbors.keySet()) {
            sb.append("\n   ").append(vertex).append(" -> ").append(neighbors.get(vertex));
        }

        return sb.toString();
    }

}
