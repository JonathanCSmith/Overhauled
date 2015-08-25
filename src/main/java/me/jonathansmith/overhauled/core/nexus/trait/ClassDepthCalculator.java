package me.jonathansmith.overhauled.core.nexus.trait;

import java.util.LinkedHashMap;

/**
 * Created by Jonathan Charles Smith on 25/08/15.
 * <p/>
 * Utility class for assessing the class depth from one class to another. Keeps runtime data of classes already
 * calculated. (Improves performance over time but can be a memory hog).
 */
public class ClassDepthCalculator {

    private final LinkedHashMap<HashKey, Integer> classDepth = new LinkedHashMap<HashKey, Integer>();

    public int getClassDepth(Class objectClass, Class modifierClass) {
        HashKey key = new HashKey(objectClass, modifierClass);
        if (this.classDepth.containsKey(key)) {
            return this.classDepth.get(key);
        }

        if (objectClass == modifierClass) {
            this.classDepth.put(key, 0);
            return 0;
        }

        Class[] interfaces = objectClass.getInterfaces();
        for (Class i : interfaces) {
            if (i == modifierClass) {
                HashKey interfaceKey = new HashKey(objectClass, i);
                this.classDepth.put(interfaceKey, 0);
                return 0;
            }
        }

        Class parentClass = objectClass.getSuperclass();
        if (parentClass == Object.class) {
            return -1;
        }

        int out = this.getClassDepth(parentClass, modifierClass);
        if (out == -1) {
            return -1;
        }

        else {
            out += 1;
            this.classDepth.put(key, out);
            Class[] i2s = parentClass.getInterfaces();
            for (Class i : i2s) {
                HashKey interfaceKey = new HashKey(objectClass, i);
                this.classDepth.put(interfaceKey, out);
            }

            return out;
        }
    }

    private final class HashKey {

        private final Class clazz1;
        private final Class clazz2;

        public HashKey(Class clazz1, Class clazz2) {
            this.clazz1 = clazz1;
            this.clazz2 = clazz2;
        }

        public Class getClass1() { return this.clazz1; }

        public Class getClass2() { return this.clazz2; }

        @Override
        public boolean equals(Object o) {
            if (o instanceof HashKey) {
                HashKey key = (HashKey) o;
                return this.clazz1 == key.getClass1() && this.clazz2 == key.getClass2();
            }

            return false;
        }

        @Override
        public int hashCode() {
            return this.clazz1.hashCode() * 24 + this.clazz2.hashCode();
        }
    }
}
