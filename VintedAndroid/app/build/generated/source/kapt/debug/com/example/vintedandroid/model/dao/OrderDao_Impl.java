package com.example.vintedandroid.model.dao;

import android.database.Cursor;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import com.example.vintedandroid.model.dto.OrderDto;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class OrderDao_Impl implements OrderDao {
  private final RoomDatabase __db;

  public OrderDao_Impl(RoomDatabase __db) {
    this.__db = __db;
  }

  @Override
  public List<OrderDto> getAll() {
    final String _sql = "select * from orderDto";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
      final int _cursorIndexOfPaymentid = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentid");
      final int _cursorIndexOfNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "number");
      final int _cursorIndexOfInserionId = CursorUtil.getColumnIndexOrThrow(_cursor, "inserionId");
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
      final List<OrderDto> _result = new ArrayList<OrderDto>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final OrderDto _item;
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        final String _tmpDate;
        if (_cursor.isNull(_cursorIndexOfDate)) {
          _tmpDate = null;
        } else {
          _tmpDate = _cursor.getString(_cursorIndexOfDate);
        }
        final long _tmpPaymentid;
        _tmpPaymentid = _cursor.getLong(_cursorIndexOfPaymentid);
        final int _tmpNumber;
        _tmpNumber = _cursor.getInt(_cursorIndexOfNumber);
        final long _tmpInserionId;
        _tmpInserionId = _cursor.getLong(_cursorIndexOfInserionId);
        final long _tmpUserId;
        _tmpUserId = _cursor.getLong(_cursorIndexOfUserId);
        _item = new OrderDto(_tmpId,_tmpDate,_tmpPaymentid,_tmpNumber,_tmpInserionId,_tmpUserId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public OrderDto getById(final long orderId) {
    final String _sql = "select * from orderDto where id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, orderId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
      final int _cursorIndexOfPaymentid = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentid");
      final int _cursorIndexOfNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "number");
      final int _cursorIndexOfInserionId = CursorUtil.getColumnIndexOrThrow(_cursor, "inserionId");
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
      final OrderDto _result;
      if(_cursor.moveToFirst()) {
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        final String _tmpDate;
        if (_cursor.isNull(_cursorIndexOfDate)) {
          _tmpDate = null;
        } else {
          _tmpDate = _cursor.getString(_cursorIndexOfDate);
        }
        final long _tmpPaymentid;
        _tmpPaymentid = _cursor.getLong(_cursorIndexOfPaymentid);
        final int _tmpNumber;
        _tmpNumber = _cursor.getInt(_cursorIndexOfNumber);
        final long _tmpInserionId;
        _tmpInserionId = _cursor.getLong(_cursorIndexOfInserionId);
        final long _tmpUserId;
        _tmpUserId = _cursor.getLong(_cursorIndexOfUserId);
        _result = new OrderDto(_tmpId,_tmpDate,_tmpPaymentid,_tmpNumber,_tmpInserionId,_tmpUserId);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
